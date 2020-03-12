package com.qf.bean;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 订单详情
 * order chenjia
 */
@Data
public class OrderDetail {

  private String detailId;
  private String orderId;      //订单id
  private Long productId;
  private String productName;
  private BigDecimal productPrice;
  private Long productQuantity;
  private java.util.Date createTime;
  private java.util.Date updateTime;

}
