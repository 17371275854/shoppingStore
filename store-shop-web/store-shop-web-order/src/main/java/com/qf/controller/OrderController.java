package com.qf.controller;

import com.qf.test.entity.TUser;
import com.qf.util.DataCarrier;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RestController
public class OrderController {
    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private RestTemplate restTemplate;

    /**
     * 创建订单
     * @param UUID
     * @param orderInfo * 商品id，商品数量，买家姓名，买家地址，买家电话号码 ，邮寄方式（中通、申通等等）
     * @return
     */
    @RequestMapping("/order")
    public DataCarrier createOrder(@CookieValue(name = "store_shop_login", required = false) String UUID, Map orderInfo) {
//        if(StringUtil.isNullOrEmpty(UUID)) {
//            return new DataCarrier(1,"请先登陆！！！", null);
//        }
//        Object o = redisTemplate.opsForValue().get(UUID);
//        if(o == null) {
//            return new DataCarrier(1,"请先登陆！！！", null);
//        }
        String uri = "http://STORE-SHOP-SERVICE-ORDER/createOrder";
        //********测试数据*****->
        Map<Long, Object> map = new HashMap();
        map.put(1L, 1L);
        map.put(3L, 1L);
        map.put(5L, 1L);
        map.put(7L, 1L);
        Map m = new HashMap();
        m.put("products", map);
        m.put("receiver","xiaoming");
        m.put("phone","123456");
        m.put("buyerAddress", "秦皇岛");
        TUser tUser = new TUser();
        tUser.setUname("xiaoming");
        tUser.setPassword("$2a$10$4fplFu3FaW5XP73a94gYeuUXuh9ZMRzV7649L0avvZNdTlnPzK3VS");
        tUser.setEmail("123456@qq.com");
        m.put("user", tUser);
//        restTemplate.
        //*********************<-
        HttpHeaders httpHeaders = new HttpHeaders();
        MediaType mediaType = MediaType.parseMediaType("application/json;charset=UTF-8");
        httpHeaders.setContentType(mediaType);
        httpHeaders.add("Accept", MediaType.APPLICATION_JSON.toString());
        HttpEntity httpEntity = new HttpEntity(m,httpHeaders);
        ResponseEntity<String> stringResponseEntity = null;
        try {
            stringResponseEntity = restTemplate.postForEntity(uri, httpEntity, String.class);
        } catch (RestClientException e) {
            //TODO  记录日志
            return new DataCarrier(0, "创建订单失败", null);
        }
        return new DataCarrier(0, "已成功创建订单", stringResponseEntity.getBody());
    }
}
