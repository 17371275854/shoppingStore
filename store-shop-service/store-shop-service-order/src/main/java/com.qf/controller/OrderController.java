package com.qf.controller;

import com.qf.vo.DataCarrier;
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
    public DataCarrier createOrder(@RequestBody Map res) {
//        return orderService.createOrder(orderInfo, new TUser());
//        System.out.println();
        return new DataCarrier(0, "成功", null);
    }
}
