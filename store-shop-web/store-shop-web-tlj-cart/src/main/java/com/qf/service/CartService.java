package com.qf.service;

import com.qf.constant.CookieConstant;
import com.qf.dto.ResultBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CartService {

    @Autowired
    private RestTemplate restTemplate;

    public ResultBean addProduct(String uuid,Long productId,int count){
        //调用服务的提供者并获得结果并返回

        //服务的提供者返回的是一个ResultBean
        String url = "http://STORE-SHOP-SERVICE-TLJ-CART/cart/add?uuid="+uuid+"&productId="+productId+"&count="+count;
        return restTemplate.getForObject(url,ResultBean.class);
    }

    public ResultBean delAllCart(String uuid) {
        //调用服务的提供者并获得结果并返回

        //服务的提供者返回的是一个ResultBean
        String url = "http://STORE-SHOP-SERVICE-TLJ-CART/cart/del?uuid="+uuid;
        return restTemplate.getForObject(url,ResultBean.class);
    }
}
