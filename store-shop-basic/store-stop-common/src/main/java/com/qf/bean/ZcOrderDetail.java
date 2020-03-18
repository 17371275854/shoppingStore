package com.qf.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ZcOrderDetail implements Serializable {
    private String id;
    private int orderID;
    private int productID;
    private String giftID;
    private String price;//商品单价
    private int number;//商品数量
    private String fee;// 配送费
    private String isComment;
    private String productName;
    private String total0;// 小计
    private String lowStocks;// n:库存不足；y:库存充足。默认n
    private String specInfo;//商品规格信息
}
