package com.qf.store.shop.regist.service;

import tk.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@MapperScan(basePackages = "com.qf.store.shop.regist.service.mapper")
public class StoreShopRegistServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(StoreShopRegistServiceApplication.class, args);
    }

}
