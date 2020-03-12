package com.qf.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class OrderConfig {
    @LoadBalanced
    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    //    @Bean
//    public RedisTemplate getRedisTemplate() {
//        return new RedisTemplate();
//    }
//    @Bean
//    public TopicExchange getExchange() {
//        return new TopicExchange("order_topic_exchage", true, false);
//    }
}
