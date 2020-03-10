package com.qf;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@MapperScan(basePackages = "com.qf.mapper")
public class StoreShopServiceSolrApplication {
    public static void main(String[] args) {
        SpringApplication.run(StoreShopServiceSolrApplication.class, args);
    }
}
