package com.qf.solr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class StoreShopWebSolrApplication {
    public static void main(String[] args) {
        SpringApplication.run(StoreShopWebSolrApplication.class, args);
    }
}
