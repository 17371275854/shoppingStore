package com.qf.store.shop.cache.login.redis.controller;


import com.qf.dto.ResultBean;
import com.qf.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("redis")
public class LoginController {

    @Autowired
    private RedisTemplate<String, UserDTO> redisTemplate;


    //从redis中获取用户对象
    @RequestMapping("get/userInfo")
    public ResultBean getUserInfoByKey(String redisKey){
        UserDTO userDTO = redisTemplate.opsForValue().get(redisKey);
        if (userDTO == null){
            return ResultBean.error("redis中没有登陆信息");
        }
        return ResultBean.success(userDTO);
    }


    @RequestMapping("set/userInfo")
    public ResultBean setUserInfo(String redisKey,UserDTO userDTO){

        try {
            redisTemplate.opsForValue().set(redisKey,userDTO);
        } catch (Exception e) {
            return ResultBean.error("将用户对象存入redis失败");
        }
        return ResultBean.success("用户对象存入redis成功");
    }


}
