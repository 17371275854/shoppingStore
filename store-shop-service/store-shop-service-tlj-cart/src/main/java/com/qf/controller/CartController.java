package com.qf.controller;

import com.qf.dto.ResultBean;
import com.qf.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("cart")
public class CartController {

    @Autowired
    private ICartService cartService;

    @RequestMapping("add")
    public ResultBean addProduct(String uuid,Long productId,int count){
        ResultBean resultBean = cartService.addProduct(uuid, productId, count);
        return resultBean;
    }

    @RequestMapping("del")
    public ResultBean delAllCart(String uuid){
        ResultBean resultBean = cartService.delAllCart(uuid);
        return resultBean;
    }
}
