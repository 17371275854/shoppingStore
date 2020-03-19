package com.qf.store.shop.web.regist.service;

import com.qf.dto.ResultBean;
import org.springframework.boot.context.properties.bind.DefaultValue;

public interface IRegistService {


    ResultBean regist(int registNum, String addr, String password, @DefaultValue("")String code);


    ResultBean active(String uuid);

    ResultBean sendSMS(String phoneNum);


}
