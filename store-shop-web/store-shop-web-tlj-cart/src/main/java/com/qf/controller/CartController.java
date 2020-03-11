package com.qf.controller;

import com.qf.constant.CookieConstant;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Controller
@RequestMapping("cart")
public class CartController {

    /**
     * 添加商品到购物车
     *
     * 	1）当前用户没有购物车
     * 		新建购物车，把商品添加到购物车中，再把购物车存到redis中
     * 	2）当前用户有购物车，但是购物车中没有该商品
     * 		先从redis中获取该购物车，再把商品添加都购物车中，再存入到redis中。
     * 	3）当前用户有购物车，且购物车中有该商品
     * 		先从redis中获取该购物车，再获取该商品的数量，再让新的数量和老的数量相加，更新回购物车中，再更新回redis中。
     *
     * @param productId  这一次要添加到购物车的商品的id
     * @param count   数量
     * @return
     */
    @RequestMapping("add/{productId}/{count}")
    public void addProduct(@CookieValue(name = CookieConstant.USER_CART,required = false)String uuid,
                           @PathVariable Long productId,
                           @PathVariable int count,
                           HttpServletRequest request,
                           HttpServletResponse response){

        //======================未登录状态下的购物车========================
        //把商品添加到购物车，这个购物车放在Redis中
        if (uuid == null || "".equals(uuid)) {
            //UUID为空的话就再生成一个
            uuid = UUID.randomUUID().toString();
            //返回UUID给Cookie
            Cookie cookie = new Cookie(CookieConstant.USER_CART,uuid);
            cookie.setPath("/");
            response.addCookie(cookie);
        }

    }

}
