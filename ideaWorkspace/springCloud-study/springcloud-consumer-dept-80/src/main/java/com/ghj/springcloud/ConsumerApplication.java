package com.ghj.springcloud;

import com.ghj.myLoadBalancer.MyConfig;
import com.ghj.springcloud.config.ConfigBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author 86187
 */

@SpringBootApplication
@EnableEurekaClient
@LoadBalancerClient(name = "SPRING-PROVIDER-DEPT", configuration = MyConfig.class)
public class ConsumerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConsumerApplication.class, args);
    }
}
