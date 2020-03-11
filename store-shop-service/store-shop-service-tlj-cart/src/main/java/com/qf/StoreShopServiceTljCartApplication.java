package com.qf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@EnableDiscoveryClient
//@ComponentScan(value = "com.qf.service.impl")
public class StoreShopServiceTljCartApplication {

    public static void main(String[] args) {
        SpringApplication.run(StoreShopServiceTljCartApplication.class, args);
    }

}
