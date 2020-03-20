package com.qf.store.shop.web.login;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients//开启feign
public class StoreShopWebLoginApplication {

    public static void main(String[] args) {
        SpringApplication.run(StoreShopWebLoginApplication.class, args);
    }

}
