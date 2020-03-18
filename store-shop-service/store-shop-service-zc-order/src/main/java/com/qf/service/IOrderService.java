package com.qf.service;

import com.qf.bean.OrderDTO;
import com.qf.entity.TProduct;

import java.util.Map;

public interface IOrderService {
    TProduct selectByPrimarykey(long pid);
    void createOrder(OrderDTO orderDTO);
}
