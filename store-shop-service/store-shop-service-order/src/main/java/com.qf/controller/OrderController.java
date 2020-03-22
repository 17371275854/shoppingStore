package com.qf.controller;

import com.qf.entity.TUser;
import com.qf.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;

    @RequestMapping("/createOrder")
    public String createOrder(@RequestBody Map<String, Object> res) {
//        Map my1 = (Map)res.get("products");
//        Map my2 = (Map)res.get("user");
//        //****测试数据*****->
//        TUser user = new TUser();
//        user.setUname((String)my2.get("uname"));
//        user.setPassword((String)my2.get("password"));
//        user.setEmail((String)my2.get("email"));
         return orderService.createOrder(res);
    }
}
