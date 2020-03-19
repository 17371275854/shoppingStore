package com.qf.store.shop.cache.regist.redis.controller;


import com.qf.constant.RegistConstant;
import com.qf.dto.ResultBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.transform.Result;
import java.util.concurrent.TimeUnit;

@Slf4j
@RestController
@RequestMapping("redis")
public class RegistController implements RegistConstant {


    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @RequestMapping("set/uuid/{addr}/{uuid}")
    public ResultBean setUUID(@PathVariable String addr,@PathVariable String uuid){

        //生存时间 15分钟
        String key = new StringBuilder()
                .append(RegistConstant.REGIST_UUID).append("=")
                .append(uuid).toString();
        redisTemplate.opsForValue().set(key,addr,15, TimeUnit.MINUTES);
        return ResultBean.success("添加redis缓存成功");
    }


    @RequestMapping("delete/uuid/{uuid}")
    public ResultBean deleteKey(@PathVariable String uuid){
        String key = new StringBuilder().append(RegistConstant.REGIST_UUID)
                .append("=")
                .append(uuid).toString();
        String addr = redisTemplate.opsForValue().get(key);
        if (addr == null || addr.isEmpty()){
                return ResultBean.error("redis中没有这个键");
        }else {
            Boolean delete = redisTemplate.delete(key);
            if (delete){
                System.out.println("删除redis中uuid失败");
            }
            return ResultBean.success(addr);
        }

    }

    //将发送给手机的验证码存入redis
    @RequestMapping("set/sms/{phoneNum}/{code}")
    public ResultBean setSMS(@PathVariable String phoneNum,@PathVariable String code){

        String phoneSMSKey = new StringBuilder()
                .append(RegistConstant.REGIST_SMS)
                .append("=")
                .append(phoneNum).toString();

        redisTemplate.opsForValue().set(phoneSMSKey,code,5,TimeUnit.MINUTES);
        return ResultBean.success("手机验证码存入redis成功");
    }


    //校对输入的验证码是否跟redis中的一致
    @RequestMapping("verify/sms/{phoneNum}/{code}")
    public ResultBean verifySMS(@PathVariable String phoneNum,@PathVariable String code){
        String key = new StringBuilder()
                .append(RegistConstant.REGIST_SMS)
                .append("=")
                .append(phoneNum).toString();

        String value = redisTemplate.opsForValue().get(key);
        if (code!=null && !code.isEmpty() && code.equals(value)){
            Boolean delete = redisTemplate.delete(key);
            if (!delete){
                log.error("删除redis中手机验证码键值对失败");
            }
            return ResultBean.success("校验成功");
        }else {
            return ResultBean.error("校验失败");
        }

    }





}
