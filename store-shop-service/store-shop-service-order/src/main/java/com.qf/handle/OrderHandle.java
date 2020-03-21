package com.qf.handle;

import com.qf.exception.OrderException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderHandle {
    @ExceptionHandler({OrderException.class})
    public String OrderHandle(OrderException e){
        return e.getMessage();
    }
}
