package com.qf.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang.RandomStringUtils;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单详情
 * 当创建该对象时，id随机生成
 * Author chenjia
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetail implements Serializable {

  private String detailId ;
  private String orderId;      //订单id
  private Long productId;
  private String productName;
  private BigDecimal productPrice;
  private Long productQuantity;
  private Date createTime;
  private Date updateTime;
  {
    detailId = RandomStringUtils.randomAlphanumeric(4) + new Date().getTime(); //生成4位随机字符串 + 当前时间毫秒值
  }

}
