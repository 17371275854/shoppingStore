package com.qf.store.shop.cache.regist.redis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class StoreShopCacheRegistRedisApplication {

    public static void main(String[] args) {
        SpringApplication.run(StoreShopCacheRegistRedisApplication.class, args);
    }

}
