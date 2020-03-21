package com.qf.controller;

import com.qf.TUser;
import com.qf.bean.Order;
import com.qf.constant.RedisConstant;
import com.qf.entity.CartBean;
import com.qf.entity.TProduct;
import com.qf.service.IOrderService;
import com.qf.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("order")
@Slf4j
public class OrderController {
    @Autowired
    private IOrderService orderService;
    @Autowired
    private RedisTemplate redisTemplate;

    /**
     *  购物车点击结算跳转到订单确认页面
     */
    @RequestMapping("orderConfirm")
    public String orderConfirm(HttpSession session, Model model){
        //------测试数据Start-------
        //1,用户信息
        TUser tUser = new TUser();
        tUser.setUid(888L);
        tUser.setUname("zhangsan");
        //2.结算商品
        List<CartBean> cartBeanList = new ArrayList<>();
        CartBean cart1 = new CartBean();
        CartBean cart2 = new CartBean();
        cart1.setCount(2);
        cart1.setProductId(1L);
        cart2.setCount(2);
        cart2.setProductId(4L);
        cartBeanList.add(cart1);
        cartBeanList.add(cart2);
        //3.将结算商品放入Redis中
        String redisKey = StringUtil.getRedisKey(RedisConstant.USER_CART_PRE, tUser.getUid().toString());
        redisTemplate.opsForValue().set(redisKey,cartBeanList);
        session.setAttribute("user",tUser);
        ArrayList<TProduct> productArrayList = new ArrayList<>();
        for (CartBean cartBean : cartBeanList) {
            TProduct tProduct = new TProduct();
            tProduct = orderService.selectByPrimarykey(cartBean.getProductId());
            productArrayList.add(tProduct);
        }
        model.addAttribute("productArrayList",productArrayList);
        return "orderConfirm";
    }

    /**
     *  点击结算
     */
    @RequestMapping(value = "pay",method = RequestMethod.GET)
    public String pay(Order order, HttpServletRequest request, HttpSession session){




        return "forward:/doPay";
    }

}
