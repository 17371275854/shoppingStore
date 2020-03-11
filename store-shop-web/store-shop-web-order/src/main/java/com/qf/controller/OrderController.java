package com.qf.controller;

import com.qf.util.DataCarrier;
import io.netty.util.internal.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {
    @Autowired
    private RedisTemplate redisTemplate;
    @RequestMapping("order")
    public DataCarrier createOrder(@CookieValue(name = "store_shop_userId", required = false) String UUID, String token) {
        if(StringUtil.isNullOrEmpty(UUID)) {
            return new DataCarrier(1,"请先登陆！！！", null);
        }
        return null;
    }
}
