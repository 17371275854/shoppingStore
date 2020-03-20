package com.qf.service.impl;

import com.qf.bean.Order;
import com.qf.bean.OrderDTO;
import com.qf.bean.ZcOrderDetail;
import com.qf.entity.TProduct;
import com.qf.mapper.OrderMapper;
import com.qf.mapper.OrderdetailMapper;
import com.qf.mapper.ProductMapper;
import com.qf.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class OrderService implements IOrderService {
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private OrderdetailMapper orderdetailMapper;
    @Autowired
    private ProductMapper productMapper;

    @Override
    public void createOrder(OrderDTO orderDTO) {
        Order order = orderDTO.getOrder();
        List<ZcOrderDetail> orderdetailList = orderDTO.getOrderDetailList();
        //创建订单
        orderMapper.insertByOrder(order);
        //创建订单明细
        for (ZcOrderDetail o : orderdetailList) {
            orderdetailMapper.insertByOrderdetail(o);
        }
    }

    @Override
    public TProduct selectByPrimarykey(long pid) {
        return productMapper.selectByPrimarykey(pid);
    }
}
