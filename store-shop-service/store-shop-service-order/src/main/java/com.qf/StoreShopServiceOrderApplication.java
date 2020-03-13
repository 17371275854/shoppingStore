package com.qf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@EnableEurekaClient
@MapperScan(basePackages = "com.qf.mapper")
public class StoreShopServiceOrderApplication {
    public static void main(String[] args) {
        SpringApplication.run(StoreShopServiceOrderApplication.class, args);
    }
}
