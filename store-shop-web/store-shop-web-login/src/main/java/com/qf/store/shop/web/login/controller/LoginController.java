package com.qf.store.shop.web.login.controller;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qf.constant.RegistConstant;
import com.qf.dto.ResultBean;
import com.qf.dto.UserDTO;
import com.qf.store.shop.web.login.service.LoginRedisService;
import com.qf.store.shop.web.login.service.LoginUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@RestController
@RequestMapping("login")
public class LoginController implements RegistConstant {


    @Autowired
    private LoginRedisService loginRedisService;


    @Autowired
    private LoginUserService loginUserService;

    @RequestMapping(path = "user",method = RequestMethod.POST)
    public ResultBean userLogin(@RequestParam String username,
                                @RequestParam String password,
                                @CookieValue(value = REDIS_USER_KEY,required = false)String uuid,
                                HttpServletResponse response){


        ResultBean resultBean = null;
        String redisKey = "";
        Boolean newRedisKeyFlag = false;
        //请求中是否带有uuid
        if (uuid != null && !uuid.equals("")){
          String redisKey1 = new StringBuilder().append(REDIS_USER_KEY)
                  .append("=")
                  .append(uuid).toString();
          //组织键后 看从redis中能否取用户，能取到则表示已登陆，
          resultBean = loginRedisService.getUserInfoByKey(redisKey1);
          redisKey = redisKey1;

        }
        //如果从redis中没有取到
        if (resultBean == null || resultBean.getErrno() == 1){
            //从数据库中获取用户
            if (username.contains("@")){
                //邮件登陆
                resultBean = loginUserService.userLogin("email", username);
            }else {
                resultBean = loginUserService.userLogin("phone", username);
            }
            if (resultBean.getErrno() == 1){
                return resultBean;
            }
            newRedisKeyFlag = true;
        }


        //数据库中没有该用户
        if (resultBean.getErrno() == 1){
            return resultBean;
        }

        Object data = resultBean.getData();
        ObjectMapper objectMapper = new ObjectMapper();
        UserDTO userDTO = objectMapper.convertValue(data, new TypeReference<UserDTO>() {
        });

        //校验密码
        if (!userDTO.getPassword().equals(password)){
            return ResultBean.error("密码错误");
        }


        if (newRedisKeyFlag){
        //需要重新生成用户信息键值对，并存到redis中
            String new_uuid = UUID.randomUUID().toString();
            String redisKey2 = new StringBuilder().append(REDIS_USER_KEY)
                    .append("=")
                    .append(new_uuid).toString();
            redisKey = redisKey2;
            //将新的用户信息存到redis中
            loginRedisService.setUserInfo(redisKey,userDTO);
            //将redis中的key放到cookie中，设置生存时间
            Cookie cookie = new Cookie(REDIS_USER_KEY,new_uuid);
            cookie.setMaxAge(86400);
            cookie.setPath("/");
            response.addCookie(cookie);
        }else {
            //不需要重新生成用户信息键值对，重置redis中用户的生存时间
            loginRedisService.setUserInfo(redisKey,userDTO);
            //并重置cookie生存时间
            Cookie cookie = new Cookie(REDIS_USER_KEY,uuid);
            cookie.setMaxAge(86400);
            cookie.setPath("/");
            response.addCookie(cookie);

        }


        return ResultBean.success("登陆成功");
    }

}
