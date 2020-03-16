package com.qf.store.shop.regist.service.controller;


import com.qf.dto.ResultBean;
import com.qf.dto.UserDTO;
import com.qf.store.shop.regist.service.entity.RegistUser;
import com.qf.store.shop.regist.service.mapper.UserMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;

@RestController
@RequestMapping("mapper")
public class RegistController {


    @Autowired(required = false)
    private UserMapper userMapper;

    @RequestMapping("email")
    public ResultBean registEmail(@RequestBody UserDTO userDTO){

        RegistUser registUser = new RegistUser();
        BeanUtils.copyProperties(userDTO,registUser);

        int i = userMapper.insert(registUser);
        if (i == 1){
            return ResultBean.success("存入数据库成功");
        }else {
            return ResultBean.error("存入数据库失败");
        }
    }

    //修改数据库中的状态
    @RequestMapping("active/{email}")
    public ResultBean activeAccount(@PathVariable String email){

        RegistUser registUser = new RegistUser();
        registUser.setFlag(1);
        registUser.setUpdateTime(new Date());

        Example example = new Example(RegistUser.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("email",email);

        //修改
        int i = userMapper.updateByExampleSelective(registUser, example);
        if (i == 0){
            return ResultBean.error("激活失败");
        }else {
            return ResultBean.success("激活成功");
        }

    }




}
