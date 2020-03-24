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
         return orderService.createOrder(res);
    }
}
