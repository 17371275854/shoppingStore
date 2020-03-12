package com.qf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class StoreShopWebTljCartApplication {

    public static void main(String[] args) {
        SpringApplication.run(StoreShopWebTljCartApplication.class, args);
    }

}
