package com.qf.service;

import com.qf.bean.TUser;
import com.qf.vo.DataCarrier;

import java.util.Map;

public interface OrderService {
    DataCarrier createOrder(Map orderInfo, TUser user);
}
