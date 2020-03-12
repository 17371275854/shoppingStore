package com.qf.bean;

import lombok.Data;
import org.apache.commons.lang.RandomStringUtils;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单详情
 * 当创建该对象时，id随机生成
 * Author chenjia
 */
@Data
public class OrderDetail {

  private String detailId ;
  private String orderId;      //订单id
  private Long productId;
  private String productName;
  private BigDecimal productPrice;
  private Long productQuantity;
  private java.util.Date createTime;
  private java.util.Date updateTime;
  {
    detailId = RandomStringUtils.randomAlphanumeric(4) + new Date().getTime(); //生成4位随机字符串 + 当前时间毫秒值
  }

}
