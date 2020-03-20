package com.qf.store.shop.cache.login.redis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class StoreShopCacheLoginRedisApplication {

    public static void main(String[] args) {
        SpringApplication.run(StoreShopCacheLoginRedisApplication.class, args);
    }

}
