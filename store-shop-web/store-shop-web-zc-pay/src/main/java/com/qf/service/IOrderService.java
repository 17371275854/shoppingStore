package com.qf.service;

import com.qf.bean.OrderDTO;
import com.qf.entity.TProduct;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("store-shop-service-zc-order")
public interface IOrderService {
    @RequestMapping("order/createOrder")
    void createOrder(@RequestBody OrderDTO orderDTO);

    @RequestMapping("order/selectProduct")
    TProduct selectByPrimarykey(@RequestParam("pid") long pid);
}
