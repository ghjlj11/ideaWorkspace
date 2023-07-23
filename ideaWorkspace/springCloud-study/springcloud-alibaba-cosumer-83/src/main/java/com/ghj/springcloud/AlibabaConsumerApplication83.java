package com.ghj.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author 86187
 */

@SpringBootApplication
@EnableDiscoveryClient
public class AlibabaConsumerApplication83 {
    public static void main(String[] args) {
        SpringApplication.run(AlibabaConsumerApplication83.class, args);
    }
}
