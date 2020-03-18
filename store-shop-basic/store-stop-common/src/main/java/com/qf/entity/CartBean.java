package com.qf.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class CartBean implements Serializable {

    private Long ProductId;
    private int count;
    private Date Time;
}
