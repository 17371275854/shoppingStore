package com.qf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class StoreShopWebOrderApplication {
    public static void main(String[] args) {
        SpringApplication.run(StoreShopWebOrderApplication.class, args);
    }
}
