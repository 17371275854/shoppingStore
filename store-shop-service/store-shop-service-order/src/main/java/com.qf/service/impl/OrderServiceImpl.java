package com.qf.service.impl;

import com.qf.bean.OrderMaster;
import com.qf.bean.TProduct;
import com.qf.bean.TUser;
import com.qf.mapper.OrderDetailMapper;
import com.qf.mapper.OrderMasterMapper;
import com.qf.mapper.ProductMapper;
import com.qf.service.OrderService;
import com.qf.vo.DataCarrier;
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
    public DataCarrier createOrder(Map orderInfo, TUser user) {
        Example example = new Example(TProduct.class);
        Example.Criteria criteria = example.createCriteria();
        Map products = (Map)orderInfo.get("products");
        Set<Long> pids = products.keySet();
        criteria.andIn("pid", pids);
        List<TProduct> tProducts = productMapper.selectByExample(example);
//        redisTemplate.opsForValue().get("s");
        /**添加订单**/
        OrderMaster orderMaster = new OrderMaster();
        int i = insertOrderMaster(orderInfo, products, tProducts, orderMaster);
        int j = insertOrderDetail(tProducts, orderMaster.getOrderId());
        if( (i + j) != 2) {
//            throw
        }
        return new DataCarrier(0, "已成功创建订单", null);
    }

    private int insertOrderMaster(Map orderInfo, Map products, List<TProduct> tProducts, OrderMaster orderMaster) {
        String receiver = orderInfo.get("receiver").toString();
        String phone = orderInfo.get("phone").toString();
        String buyerAddress = orderInfo.get("buyerAddress").toString();
        orderMaster.setBuyerName(receiver);
        orderMaster.setBuyerPhone(phone);
        orderMaster.setBuyerAddress(buyerAddress);
        BigDecimal orderAmount = new BigDecimal(0);
        for (TProduct tProduct : tProducts) {
            Long pid = tProduct.getPid();
            long quantity = Long.parseLong(products.get(pid).toString());
            orderAmount.add(tProduct.getPrice().multiply(new BigDecimal(quantity)));
        }
        orderMaster.setOrderAmount(orderAmount);
        return orderMasterMapper.insertSelective(orderMaster);
    }

    private int insertOrderDetail(List<TProduct> tProducts, String orderId) {
        return 0;
    }
}
