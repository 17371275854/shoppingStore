package com.qf.controllor;

import com.qf.bean.OrderDTO;
import com.qf.entity.TProduct;
import com.qf.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("order")
public class OrderController {
    @Autowired
    private IOrderService orderService;

    @RequestMapping("createOrder")
    public void createOrder(@RequestBody OrderDTO orderDTO){
        orderService.createOrder(orderDTO);
    }

    @RequestMapping("selectProduct")
    public TProduct selectByPrimarykey(long pid){
        //创建订单Detail
        return orderService.selectByPrimarykey(pid);
    }

}
