package com.qf.service.impl;

import com.qf.entity.CartBean;
import com.qf.entity.TProduct;
import com.qf.constant.RedisConstant;
import com.qf.dto.ResultBean;
import com.qf.pojo.Product;
import com.qf.mapper.TProductMapper;
import com.qf.service.ICartService;
import com.qf.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CartServiceImpl implements ICartService {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired(required = false)
    private TProductMapper productMapper;

    /**
     * 	1）当前用户没有购物车
     * 		新建购物车，把商品添加到购物车中，再把购物车存到redis中
     * 	2）当前用户有购物车，但是购物车中没有该商品
     * 		先从redis中获取该购物车，再把商品添加都购物车中，再存入到redis中。
     * 	3）当前用户有购物车，且购物车中有该商品
     * 		先从redis中获取该购物车，再获取该商品的数量，再让新的数量和老的数量相加，更新回购物车中，再更新回redis中。
     *
     * @param uuid
     * @param productId
     * @param count
     * @return
     */
    @SuppressWarnings("Duplicates")//压制重复代码
    @Override
    public ResultBean addProduct(String uuid, Long productId, int count) {

        //用UUID封装购物车存放在Redis中的键
        String redisCartKey = StringUtil.getRedisKey(RedisConstant.USER_CART,uuid);
        //获取用户购物车
        Object o = redisTemplate.opsForValue().get(redisCartKey);
          if (o == null){
            //第一种情况
            //将商品信息进行封装
            CartBean cartBean = new CartBean();
            cartBean.setProductId(productId);
            cartBean.setCount(count);
            cartBean.setTime(new Date());

            //创建购物车集合
            List<CartBean> cartList = new ArrayList<>();
            cartList.add(cartBean);
            //将购物车存放到Redis中
            redisTemplate.opsForValue().set(redisCartKey,cartList);
            return ResultBean.success(cartList,"添加成功");
        }
        //第二和第三种情况
        //拿到购物车集合
        List<CartBean> cartList = (List<CartBean>) o;
        //当前用户有购物车且购物车中有该商品
        for (CartBean cartBean : cartList) {
            if (cartBean.getProductId().longValue() == productId.longValue()) {
                //增加商品数量
                cartBean.setCount(cartBean.getCount()+count);
                //更新添加商品的时间
                cartBean.setTime(new Date());
                //将更新后的购物车放回Redis中
                redisTemplate.opsForValue().set(redisCartKey,cartList);
                return ResultBean.success(cartList,"添加成功");
            }
        }
        //当前用户有购物车但是购物车中没有当前商品
        CartBean cartBean = new CartBean();
        cartBean.setProductId(productId);
        cartBean.setCount(count);
        cartBean.setTime(new Date());
        cartList.add(cartBean);
        //将购物车重新放回Redis中
        redisTemplate.opsForValue().set(redisCartKey,cartList);
        return ResultBean.success(cartList,"添加成功");
    }

    @Override
    public ResultBean delAllCart(String uuid) {

        //删除Redis中的购物车
        String redisKey = StringUtil.getRedisKey(RedisConstant.USER_CART, uuid);
        redisTemplate.delete(redisKey);
        return ResultBean.success("清空购物车成功");
    }

    @Override
    public ResultBean queryCart(String uuid) {

        //用UUID封装购物车存放在Redis中的键
        String redisCartKey = StringUtil.getRedisKey(RedisConstant.USER_CART,uuid);
        //获取用户购物车
        Object o = redisTemplate.opsForValue().get(redisCartKey);
        if (o == null) {
            return ResultBean.error("当前用户没有购物车");
        }
        //购物车集合
        List<CartBean> cartList = (List<CartBean>) o;
        //购物车商品具体信息集合
        List<TProduct> productsList = new ArrayList<>();
        for (CartBean cartBean : cartList) {
            Product product = productMapper.selectByPrimaryKey(cartBean.getProductId());
            TProduct tProduct = new TProduct(product.getPid(),
                    product.getPname(),
                    product.getPrice(),
                    product.getSalePrice(),
                    product.getTypeId(),
                    product.getStatus(),
                    product.getPimage(),
                    product.getFlag(),
                    product.getCreateTime(),
                    product.getUpdateTime(),
                    product.getCreateUser(),
                    product.getUpdateUser(),
                    cartBean.getCount());
            productsList.add(tProduct);
        }

        return ResultBean.success(productsList,"查询成功");
    }

    @Override
    public ResultBean updateCart(String uuid, Long productId, int operator) {

        //用UUID封装购物车存放在Redis中的键
        String redisCartKey = StringUtil.getRedisKey(RedisConstant.USER_CART,uuid);
        //获取用户购物车
        Object o = redisTemplate.opsForValue().get(redisCartKey);

        //拿到购物车集合
        List<CartBean> cartList = (List<CartBean>) o;
        //当前用户有购物车且购物车中有该商品
        for (CartBean cartBean : cartList) {
            if (cartBean.getProductId().longValue() == productId.longValue()) {
                //更改商品数量
                cartBean.setCount(cartBean.getCount() + operator);

                //更新商品的时间
                cartBean.setTime(new Date());

                //将更新后的购物车放回Redis中
                redisTemplate.opsForValue().set(redisCartKey,cartList);
                return ResultBean.success(cartList,"修改成功");
            }
        }

        return ResultBean.error("修改失败");
    }

    @Override
    public ResultBean merge(String uuid, String userId) {
        /*
            合并
            1.未登录状态下没有购物车==》合并成功
            2.未登录状态下有购物车，但已登录状态下没有购物车==》把未登录的变成已登录的
            3.未登录状态下有购物车，但已登录状态下也有购物车，而且购物车中的商品有重复==》难点！
         */
        String noLoginRedisKey = StringUtil.getRedisKey(RedisConstant.USER_CART, uuid);
        String loginRedisKey = StringUtil.getRedisKey(RedisConstant.USER_CART, userId);
        Object noLogin = redisTemplate.opsForValue().get(noLoginRedisKey);//未登录时的购物车
        Object Login = redisTemplate.opsForValue().get(loginRedisKey);//登录状态的购物车
        if (noLogin == null) {
            //1
            return ResultBean.success("当前没有购物车，不需要合并");
        }
        if (Login == null) {
            //2
            redisTemplate.opsForValue().set(loginRedisKey,noLogin);
            //合并成功，删除未登录购物车
            redisTemplate.delete(noLoginRedisKey);
            return ResultBean.success(noLogin,"合并成功");
        }

        //3
        List<CartBean> noLoginCarts = (List<CartBean>) noLogin;
        List<CartBean> loginCarts = (List<CartBean>) Login;
        Map<Long, CartBean> map = new HashMap<>();
        for (CartBean noLoginCart : noLoginCarts) {
            //将未登录时购物车中的商品存入map中
            map.put(noLoginCart.getProductId(),noLoginCart);
        }
        //存入以登录
        for (CartBean loginCart : loginCarts) {
            //先尝试获取，查看map中是否有当前商品
            CartBean currentcartBean = map.get(loginCart.getProductId());
            if (currentcartBean != null) {
                //已存在
                currentcartBean.setCount(currentcartBean.getCount() + loginCart.getCount());
            }else {
                //不存在
                map.put(currentcartBean.getProductId(),loginCart);
            }
        }
        //此时map中就是合并之后的购物车
        //删除未登录购物车
        redisTemplate.delete(noLoginRedisKey);
        //把合并后的购物车存入redis
        Collection<CartBean> values = map.values();
        List<CartBean> newCarts = new ArrayList<>(values);
        redisTemplate.opsForValue().set(loginRedisKey,newCarts);
        return ResultBean.success(newCarts,"合并成功");

    }

}
