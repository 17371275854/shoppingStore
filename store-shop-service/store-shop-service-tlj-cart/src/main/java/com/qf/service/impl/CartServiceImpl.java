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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
//                if ("+".equals(operator)) {
//                    cartBean.setCount(cartBean.getCount() + 1);
//                }else if ("-".equals(operator)) {
//                    cartBean.setCount(cartBean.getCount() - 1);
//                }
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
        return null;
    }

}
