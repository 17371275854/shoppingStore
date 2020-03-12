package com.sample;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TProduct {

  private Long pid;
  private String pname;
  private BigDecimal price;
  private BigDecimal salePrice;
  private Long typeId;
  private Short status;
  private String pimage;
  private Short flag;
  private java.util.Date createTime;
  private java.util.Date  updateTime;
  private Long createUser;
  private Long updateUser;
  private Long totalSales;
  private Long appraisal;
  private Long brandId;

}
