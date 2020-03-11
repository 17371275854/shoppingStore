package com.qf.util;


import java.io.Serializable;

/**
 * 用于携带数据返回给页面
 */
public class DataCarrier implements Serializable {
    private Integer code;
    private String message;
    private Object data;

    public DataCarrier(Integer code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }
}
