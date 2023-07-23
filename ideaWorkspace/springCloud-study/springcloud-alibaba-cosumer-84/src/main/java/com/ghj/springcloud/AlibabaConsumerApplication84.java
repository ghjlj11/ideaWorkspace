package com.ghj.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author 86187
 */

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class AlibabaConsumerApplication84 {
    public static void main(String[] args) {
        SpringApplication.run(AlibabaConsumerApplication84.class, args);
    }
}
