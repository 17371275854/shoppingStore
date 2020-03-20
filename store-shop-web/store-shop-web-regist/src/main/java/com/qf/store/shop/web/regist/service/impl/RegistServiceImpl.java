package com.qf.store.shop.web.regist.service.impl;


import com.qf.dto.ResultBean;
import com.qf.dto.UserDTO;
import com.qf.store.shop.web.regist.service.IRegistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Service
public class RegistServiceImpl implements IRegistService {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public ResultBean regist(int registNum, String addr, String password, @DefaultValue("")String code) {



        //邮件注册
        if (registNum == 1){
            UserDTO userDTO = new UserDTO();
            userDTO.setUname(addr);
            userDTO.setPassword(password);
            userDTO.setFlag(0);
            //生成uuid 发送邮件
            String uuid = UUID.randomUUID().toString();
            String email_url = String.format("http://store-shop-sendmail-service/email/send/%s/%s", addr, uuid);
            ResultBean resultBean = restTemplate.getForObject(email_url, ResultBean.class);

            //存入redis
            String redis_url = String.format("http://store-shop-cache-regist-redis/redis/set/uuid/%s/%s",addr,uuid);
            restTemplate.getForObject(redis_url,ResultBean.class);

            //将邮箱和密码存到数据库
            ResultBean resultBean1 = restTemplate.postForObject("http://regist-service/mapper/email", userDTO, ResultBean.class);
            System.out.println(resultBean1.getMessage());



        }else if (registNum == 2){


        }

        return ResultBean.success("注册成功!");
    }



    //激活  修改数据库 删除redis
    @Override
    public ResultBean active(String uuid) {
        //调取服务删除redis中的键值对
        String key ="http://store-shop-cache-regist-redis/redis/delete/" + uuid;
        ResultBean resultBean = restTemplate.getForObject(key, ResultBean.class);
        if (resultBean.getErrno()==0){
            //删除成功
            ResultBean mysqlResultBean = restTemplate.getForObject("http://regist-service/mapper/active/" + resultBean.getMessage(), ResultBean.class);
            return mysqlResultBean;
        }else{
            //删除失败
            return ResultBean.error("您的激活链接已过期，请重新申请链接！");
        }
    }

    //发送手机验证码
    @Override
    public ResultBean sendSMS(String phoneNum) {
        //生成验证码
        int smsCode = (int) ((Math.random() * 9 + 1) * 1000);
        String sms_code = String.valueOf(smsCode);
        //调用服务发送验证码
        String send_sms_url = String.format("http://store-shop-sendsms-service/sms/send/%s/%s",phoneNum,sms_code);
        ResultBean resultBean = restTemplate.getForObject(send_sms_url, ResultBean.class);

        //调用服务将手机验证码存入redis
        String sms_redis_url = String.format("http://store-shop-cache-regist-redis/redis/set/sms/%s/%s",phoneNum,sms_code);
        ResultBean resultBean1 = restTemplate.getForObject(sms_redis_url, ResultBean.class);

        return ResultBean.success("发送验证码成功!验证码5分钟内有效");
    }
}
