package com.qf.service.impl;

import com.qf.dto.ResultBean;
import com.qf.service.ICartService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
public class CartServiceImpl implements ICartService {

    /**
     * 	1）当前用户没有购物车
     * 		新建购物车，把商品添加到购物车中，再把购物车存到redis中
     * 	2）当前用户有购物车，但是购物车中没有该商品
     * 		先从redis中获取该购物车，再把商品添加都购物车中，再存入到redis中。
     * 	3）当前用户有购物车，且购物车中有该商品
     * 		先从redis中获取该购物车，再获取该商品的数量，再让新的数量和老的数量相加，更新回购物车中，再更新回redis中。
     *
     * @param uuid
     * @param productId
     * @param count
     * @return
     */
    @Override
    public ResultBean addProduct(String uuid, Long productId, int count) {
        return null;
    }

}
