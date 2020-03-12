package com.sample;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TUser implements Serializable {

  private Long id;
  private String uname;
  private String password;
  private String phone;
  private String email;
  private Short flag;
  private java.util.Date createTime;
  private Long createUser;
  private java.util.Date updateTime;
  private Long updateUser;

}
