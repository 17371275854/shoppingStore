package com.qf.store.shop.cache.regist.redis.controller;


import com.qf.constant.RegistConstant;
import com.qf.dto.ResultBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.transform.Result;
import java.util.concurrent.TimeUnit;

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


}
