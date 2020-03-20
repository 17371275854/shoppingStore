package com.qf.store.shop.web.login.service;

import com.qf.dto.ResultBean;
import com.qf.store.shop.web.login.service.fallback.LoginUserServiceHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(path = "regist-service",fallback = LoginUserServiceHystrix.class)
public interface LoginUserService {

    @RequestMapping("login/user")
    ResultBean userLogin(@RequestParam String flag,@RequestParam String username);


}
