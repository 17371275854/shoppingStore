package com.qf.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO implements Serializable {


    private Integer id;

    private String uname;

    private String password;

    private String phone;

    private String email;

    private Integer flag;

    private java.util.Date createTime;

    private Integer createUser;

    private java.util.Date updateTime;

    private Integer updateUser;


}
