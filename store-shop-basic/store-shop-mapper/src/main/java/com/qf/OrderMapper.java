package com.qf;

public interface OrderMapper {
    void insertByOrder(String id,String account,String createdate,
                       String status,String paystatus,String remark,String amount);
}
