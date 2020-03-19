package com.qf.store.shop.regist.service.controller;


import com.qf.dto.ResultBean;
import com.qf.dto.UserDTO;
import com.qf.store.shop.regist.service.entity.RegistUser;
import com.qf.store.shop.regist.service.mapper.UserMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.mapper.entity.Example;

@RestController
@RequestMapping("login")
public class LoginController {


    @Autowired
    private UserMapper userMapper;


    @RequestMapping("user")
    public ResultBean userLogin(@RequestParam String flag,@RequestParam String username){
        Example example = new Example(RegistUser.class);
        Example.Criteria criteria = example.createCriteria();
        if ("phone".equals(flag)){
            criteria.andEqualTo("phone",username);
        }else if ("email".equals(flag)){
            criteria.andEqualTo("email",username);
        }
        RegistUser registUser = userMapper.selectOneByExample(example);
        if (registUser == null){
            return ResultBean.error("登陆失败，用户 不存在");
        }

        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(registUser,userDTO);

        return ResultBean.success(registUser);
    }
}
