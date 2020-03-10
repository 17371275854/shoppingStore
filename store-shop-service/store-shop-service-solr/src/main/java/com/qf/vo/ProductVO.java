package com.qf.vo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductVO {
    private Long pid;
    private String pname;           //产品名称
    private BigDecimal salePrice;   //销售价格
    private String cname;           //类型名称
    private Long totalSales;        //总销量
    private Long appraisal;         //评价总数
}
