package com.qf.controller;

import com.qf.constant.CookieConstant;
import com.qf.dto.ResultBean;
import com.qf.entity.TUser;
import com.qf.service.CartService;
import com.qf.service.ICartService;
import jdk.nashorn.internal.ir.annotations.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Controller
@RequestMapping("cart")
public class CartController {

    @Autowired(required = false)
    private CartService cartService;

    @RequestMapping("/shopcart")
    public String shopcart(){
        System.out.println("!!!");
        return "shopcart";
    }

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
    @ResponseBody
    public ResultBean addProduct(@CookieValue(name = CookieConstant.USER_CART,required = false)String uuid,
                           @PathVariable Long productId,
                           @PathVariable int count,
                           HttpServletRequest request,
                           HttpServletResponse response){

        Object o = request.getAttribute("user");
        if(o!=null){
            //================已登录状态下的购物车======================= redis:    user:cart:userId
            TUser user = (TUser) o;
            Long userId = user.getId();
            return cartService.addProduct(userId.toString(),productId,count);

        }

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
        ResultBean resultBean = cartService.addProduct(uuid, productId, count);

        return resultBean;

    }

    /**
     * 清空购物车
     * @param uuid
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("del")
    @ResponseBody
    public ResultBean delAllCart(@CookieValue(name = CookieConstant.USER_CART,required = false)String uuid,
                                 HttpServletRequest request,
                                 HttpServletResponse response){

        Object o = request.getAttribute("user");
        if(o!=null){
            //================已登录状态下的购物车======================= redis:    user:cart:userId
            TUser user = (TUser) o;
            Long userId = user.getId();
            return cartService.delAllCart(userId.toString());

        }

        //============未登录状态==============
        if (uuid != null && !"".equals(uuid)) {
            //删除Cookie中的uuid
            Cookie cookie = new Cookie(CookieConstant.USER_CART,"");
            cookie.setPath("/");
            cookie.setMaxAge(0);
            response.addCookie(cookie);

            //删除Redis中存放的购物车
            return cartService.delAllCart(uuid);
        }

        return ResultBean.error("当前用户没有购物车");
    }

    /**
     * 查询购物车
     * @param uuid
     * @param request
     * @return
     */
    @RequestMapping("query")
    @ResponseBody
    public ResultBean queryCart(@CookieValue(name = CookieConstant.USER_CART,required = false)String uuid,
                                HttpServletRequest request){

        Object o = request.getAttribute("user");
        if(o!=null){
            //================已登录状态下的购物车======================= redis:    user:cart:userId
            TUser user = (TUser) o;
            Long userId = user.getId();
            return cartService.queryProduct(userId.toString());

        }

        //============未登录状态==============
        if (uuid != null && !"".equals(uuid)){
            return cartService.queryProduct(uuid);
        }

        return ResultBean.error("当前用户没有购物车");
    }

    /**
     * 更改购物车信息
     * @param uuid
     * @param productId
     * @param operator
     * @param request
     * @return
     */
    @RequestMapping("update/{productId}/{operator}")
    @ResponseBody
    public ResultBean updateCart(@CookieValue(name = CookieConstant.USER_CART,required = false)String uuid,
                                 @PathVariable Long productId,
                                 @PathVariable String operator,
                                 HttpServletRequest request){

        Object o = request.getAttribute("user");
        if(o!=null){
            //================已登录状态下的购物车======================= redis:    user:cart:userId
            TUser user = (TUser) o;
            Long userId = user.getId();
            return cartService.updateCart(userId.toString(),productId,operator);

        }

        //============未登录状态==============
        if (uuid != null && !"".equals(uuid)){
            return cartService.updateCart(uuid,productId,operator);
        }

        return ResultBean.error("当前用户没有购物车");

    }

    /**
     * 合并购物车
     * @param uuid
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("merge")
    @ResponseBody
    public ResultBean merge(@CookieValue(name = CookieConstant.USER_CART,required = false)String uuid,
                            HttpServletRequest request,
                            HttpServletResponse response){

        //获取uuid和userId
        TUser user = (TUser) request.getAttribute("user");
        String userId = null;
        if (user != null) {
            userId = user.getId().toString();
        }

        //合并之后清空未登录时的购物车
        Cookie cookie = new Cookie(CookieConstant.USER_CART,"");
        cookie.setPath("/");
        cookie.setMaxAge(0);
        response.addCookie(cookie);

        return cartService.merge(uuid,userId);

    }

}
