package com.qf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class StoreShopEurekaServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(StoreShopEurekaServerApplication.class, args);
    }

}
