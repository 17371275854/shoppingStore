package com.qf.bean;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderMaster {

  private Long orderId;
  private String buyerName;
  private String buyerPhone;
  private String buyerAddress;
  private BigDecimal orderAmount;  //订单总金额
  private Short orderStatus;
  private Short payStatus;
  private java.sql.Timestamp createTime;
  private java.sql.Timestamp updateTime;

}
