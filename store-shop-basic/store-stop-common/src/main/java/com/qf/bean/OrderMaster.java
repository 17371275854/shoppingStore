package com.qf.bean;

import lombok.Data;
import org.apache.commons.lang.RandomStringUtils;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单
 * 当创建该对象时，id随机生成
 * Author chenjia
 */
@Data
public class OrderMaster {

  private String orderId;
  private String buyerName;
  private String buyerPhone;
  private String buyerAddress;
  private BigDecimal orderAmount;  //订单总金额
  private Short orderStatus;
  private Short payStatus;
  private java.sql.Timestamp createTime;
  private java.sql.Timestamp updateTime;
  {
    orderId = RandomStringUtils.randomAlphanumeric(4) + new Date().getTime(); //生成4位随机字符串 + 当前时间毫秒值
  }
}
