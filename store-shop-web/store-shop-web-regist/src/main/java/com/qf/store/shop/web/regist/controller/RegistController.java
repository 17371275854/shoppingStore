package com.qf.store.shop.web.regist.controller;

import com.qf.dto.ResultBean;
import com.qf.store.shop.web.regist.service.IRegistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("user")
public class RegistController {

    @Autowired
    private IRegistService registService;

    @RequestMapping("regist")
    @ResponseBody
    @CrossOrigin
    public ResultBean regist(@RequestParam int registNum,@RequestParam String addr,@RequestParam String password,@RequestParam(required = false) String code){

        ResultBean resultBean = registService.regist(registNum, addr, password,code);
        return resultBean;
    }

    @RequestMapping("active/account/{uuid}")
    @CrossOrigin
    @ResponseBody
    public ResultBean activeAccount(@PathVariable String uuid, Model model){

        ResultBean resultBean = registService.active(uuid);
        model.addAttribute("resultBean",resultBean);
        return resultBean;
    }


    @RequestMapping("sms/{phoneNum}")
    @ResponseBody
    @CrossOrigin
    public ResultBean sendSMS(@PathVariable String phoneNum){

        ResultBean resultBean = registService.sendSMS(phoneNum);
        return resultBean;
    }


}
