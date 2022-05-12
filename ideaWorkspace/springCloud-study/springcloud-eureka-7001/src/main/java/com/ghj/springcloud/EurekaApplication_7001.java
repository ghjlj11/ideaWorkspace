package com.ghj.springcloud;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author 86187
 */

@EnableEurekaServer
@SpringBootApplication
public class EurekaApplication_7001 {
    public static void main(String[] args) {
        SpringApplication.run(EurekaApplication_7001.class, args);
    }
}
