package com.qf;

public interface OrderdetailMapper {
    void insertByOrderdetail(int orderID,int productID,String price,
                             int number,String productName,String total0);
}
