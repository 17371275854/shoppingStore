package com.qf.store.shop.web.regist.service;

import com.qf.dto.ResultBean;

public interface IRegistService {


    ResultBean regist(int registNum,String addr,String password);


    ResultBean active(String uuid);


}
