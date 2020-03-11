package com.qf.bean;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderDetail {

  private Long detailId;
  private Long orderId;
  private Long productId;
  private String productName;
  private BigDecimal productPrice;
  private Long productQuantity;
  private java.util.Date createTime;
  private java.util.Date updateTime;

}
