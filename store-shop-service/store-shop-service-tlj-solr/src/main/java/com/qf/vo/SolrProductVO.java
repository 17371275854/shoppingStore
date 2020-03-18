package com.qf.vo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class SolrProductVO {

    private Long id;
    private String tProductName;             //商品名称
    private String tProductType;             //商品类型
    private BigDecimal tProductSalePrice;     //销售价格
    private String tProductPimage;    //商品图片
    private Long tProductTotalSales;          //总销量
}
