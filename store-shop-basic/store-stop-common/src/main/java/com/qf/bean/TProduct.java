package com.qf.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TProduct implements Serializable {

  private long pid;
  private String pname;
  private double price;
  private double salePrice;
  private long typeId;
  private long status;
  private String pimage;
  private long flag;
  private java.util.Date createTime;
  private java.util.Date updateTime;
  private long createUser;
  private long updateUser;
  private long totalSales;
  private long appraisal;
  private long brandId;

}
