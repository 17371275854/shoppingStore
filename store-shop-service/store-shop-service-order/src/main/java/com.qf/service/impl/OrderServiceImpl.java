package com.qf.service.impl;

import com.qf.entity.OrderDetail;
import com.qf.entity.OrderMaster;
import com.qf.entity.TProduct;
import com.qf.entity.TUser;
import com.qf.exception.OrderException;
import com.qf.mapper.OrderDetailMapper;
import com.qf.mapper.OrderMasterMapper;
import com.qf.mapper.ProductMapper;
import com.qf.service.OrderService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class OrderServiceImpl implements OrderService {
//    @Autowired
//    private RabbitTemplate rabbitTemplate;
//    @Autowired
//    private RedisTemplate redisTemplate;
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private OrderMasterMapper orderMasterMapper;
    @Autowired
    private OrderDetailMapper orderDetailMapper;

    @Override
    @Transactional
    public String createOrder(Map orderInfo) {
        //TODO 转换成功Redis
        Example example = new Example(TProduct.class);
        Example.Criteria criteria = example.createCriteria();
        Map products = (Map)orderInfo.get("products");
        Set<Long> pids = products.keySet();
        criteria.andIn("pid", pids);
        List<TProduct> tProducts = productMapper.selectByExample(example);
        //******************* Redis查询结束
//        redisTemplate.opsForValue().get("s");
        /**添加订单**/
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setUserId(1L);
        int i = insertOrderMaster(orderInfo, products, tProducts, orderMaster);
        int j = insertOrderDetail(tProducts, orderMaster.getOrderId(), products);
        if( (i + j) != 2) {
            throw new OrderException("创建订单失败");
        }
        return "已成功创建订单";
    }

    private int insertOrderMaster(Map orderInfo, Map products, List<TProduct> tProducts, OrderMaster orderMaster) {
        int i = 0;
        try {
            String receiver = orderInfo.get("receiver").toString();
            String phone = orderInfo.get("phone").toString();
            String buyerAddress = orderInfo.get("buyerAddress").toString();
            orderMaster.setBuyerName(receiver);
            orderMaster.setBuyerPhone(phone);
            orderMaster.setBuyerAddress(buyerAddress);
            BigDecimal orderAmount = new BigDecimal(0);
            for (TProduct tProduct : tProducts) {
                String pid = tProduct.getPid().toString();
                long quantity = Long.parseLong(products.get(pid).toString());
                orderAmount = orderAmount.add(tProduct.getPrice().multiply(new BigDecimal(quantity)));
            }
            orderMaster.setOrderAmount(orderAmount);
            i = orderMasterMapper.insertSelective(orderMaster);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return i;
    }

    private int insertOrderDetail(List<TProduct> tProducts, String orderId, Map products) {
        int j = 1;
        try {
            for (TProduct tProduct : tProducts) {
                OrderDetail orderDetail = new OrderDetail();
                orderDetail.setOrderId(orderId);
                orderDetail.setProductId(tProduct.getPid());
                orderDetail.setProductName(tProduct.getPname());
                orderDetail.setProductPrice(tProduct.getPrice());
                Long productQuantity = Long.parseLong(products.get(tProduct.getPid().toString()).toString());
                orderDetail.setProductQuantity(productQuantity);
                if(orderDetailMapper.insertSelective(orderDetail) == 0) {
                    j = 0;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return j;
    }
}
//    private String detailId ;
//    private String orderId;      //订单id
//    private Long productId;
//    private String productName;
//    private BigDecimal productPrice;
//    private Long productQuantity;
//    private java.util.Date createTime;
//    private java.util.Date updateTime;
