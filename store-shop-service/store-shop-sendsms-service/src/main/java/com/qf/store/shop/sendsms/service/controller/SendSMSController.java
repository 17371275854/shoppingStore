package com.qf.store.shop.sendsms.service.controller;


import com.qf.dto.ResultBean;
import com.qf.store.shop.sendsms.service.util.SmsUtil;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("sms")
public class SendSMSController {


    @RequestMapping("send/{phoneNum}/{code}")
    public ResultBean sendSMS(@PathVariable String phoneNum,@PathVariable String code){

//        SmsUtil.sendTplSms()
        return null;
    }
}
