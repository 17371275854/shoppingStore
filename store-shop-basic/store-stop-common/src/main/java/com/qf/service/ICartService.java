package com.qf.service;

import com.qf.dto.ResultBean;


public interface ICartService {

    //添加购物车
    ResultBean addProduct(String uuid,Long productId,int count);

    //清空购物车
    ResultBean delAllCart(String uuid);

    //查看购物车
    ResultBean queryCart(String uuid);

    //修改购物车
    ResultBean updateCart(String uuid, Long productId, int operator);

    //合并购物车
    ResultBean merge(String uuid, String userId);
}
