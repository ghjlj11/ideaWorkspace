package com.ghj.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Description: 
 * @author guohuanjun
 * @date 2023/7/18  23:40
 */

@SpringBootApplication
@EnableDiscoveryClient
public class AlibabaSentinelServerApplication8401 {
    public static void main(String[] args) {
        SpringApplication.run(AlibabaSentinelServerApplication8401.class, args);
    }
}
