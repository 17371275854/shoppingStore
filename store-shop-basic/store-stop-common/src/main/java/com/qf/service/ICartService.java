package com.qf.service;

import com.qf.dto.ResultBean;

public interface ICartService {

    ResultBean addProduct(String uuid,Long productId,int count);

}
