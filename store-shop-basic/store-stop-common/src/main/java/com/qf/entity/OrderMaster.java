package com.qf.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang.RandomStringUtils;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单
 * 当创建该对象时，id随机生成
 * Author chenjia
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderMaster implements Serializable {

  private String orderId;
  private String buyerName;
  private String buyerPhone;
  private String buyerAddress;
  private BigDecimal orderAmount;  //订单总金额
  private Short orderStatus;       //订单状态, 默认0 新下单
  private Short payStatus;         //支付状态, 默认0 未支付
  private java.sql.Timestamp createTime;
  private java.sql.Timestamp updateTime;
  {
    orderId = RandomStringUtils.randomAlphanumeric(4) + new Date().getTime(); //生成4位随机字符串 + 当前时间毫秒值
  }
}
