package com.qf.store.shop.web.login.service;


import com.qf.dto.ResultBean;
import com.qf.dto.UserDTO;
import com.qf.store.shop.web.login.service.fallback.LoginRedisServiceHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(path = "store-shop-cache-login-redis",fallback = LoginRedisServiceHystrix.class)
public interface LoginRedisService {

    @RequestMapping("get/userInfo")
    ResultBean getUserInfoByKey(String redisKey);

    @RequestMapping("set/userInfo")
    ResultBean setUserInfo(String redisKey, UserDTO userDTO);

}
