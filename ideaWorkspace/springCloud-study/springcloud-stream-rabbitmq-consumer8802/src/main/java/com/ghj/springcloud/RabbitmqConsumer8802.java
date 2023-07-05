package com.ghj.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author guohuanjun1
 * @date 2023/7/5
 */
@EnableEurekaClient
@SpringBootApplication
public class RabbitmqConsumer8802 {
    public static void main(String[] args) {
        SpringApplication.run(RabbitmqConsumer8802.class, args);
    }
}
