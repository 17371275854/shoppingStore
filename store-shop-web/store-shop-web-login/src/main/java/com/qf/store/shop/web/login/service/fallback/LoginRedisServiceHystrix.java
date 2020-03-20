package com.qf.store.shop.web.login.service.fallback;

import com.qf.dto.ResultBean;
import com.qf.dto.UserDTO;
import com.qf.store.shop.web.login.service.LoginRedisService;

public class LoginRedisServiceHystrix implements LoginRedisService {
    @Override
    public ResultBean getUserInfoByKey(String redisKey) {
        return ResultBean.error("触发熔断");
    }

    @Override
    public ResultBean setUserInfo(String redisKey, UserDTO userDTO) {
        return ResultBean.error("触发熔断");
    }
}
