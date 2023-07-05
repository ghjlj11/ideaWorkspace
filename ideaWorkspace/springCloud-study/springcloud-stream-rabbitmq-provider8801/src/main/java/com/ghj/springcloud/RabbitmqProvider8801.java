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
public class RabbitmqProvider8801 {
    public static void main(String[] args) {
        SpringApplication.run(RabbitmqProvider8801.class, args);
    }
}
