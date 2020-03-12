package com.qf.service;

import com.qf.dto.ResultBean;

public interface ICartService {

    //添加购物车
    ResultBean addProduct(String uuid,Long productId,int count);

    //清空购物车
    ResultBean delAllCart(String uuid);

}
