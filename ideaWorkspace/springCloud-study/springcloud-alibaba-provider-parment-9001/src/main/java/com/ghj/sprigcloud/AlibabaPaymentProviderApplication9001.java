package com.ghj.sprigcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author 86187
 */

@SpringBootApplication
@EnableDiscoveryClient
public class AlibabaPaymentProviderApplication9001 {
    public static void main(String[] args) {
        SpringApplication.run(AlibabaPaymentProviderApplication9001.class, args);
    }
}
