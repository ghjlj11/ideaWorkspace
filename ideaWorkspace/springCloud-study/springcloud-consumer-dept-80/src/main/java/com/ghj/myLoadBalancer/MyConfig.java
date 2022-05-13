package com.ghj.myLoadBalancer;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.cloud.loadbalancer.core.ReactorServiceInstanceLoadBalancer;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 86187
 */

@Configuration
public class MyConfig {

    @Bean
    public ReactorServiceInstanceLoadBalancer myLoadBalancer(ObjectProvider<ServiceInstanceListSupplier> serviceInstanceListSupplierProvider){
        return new MyRandomLoadBalancer(serviceInstanceListSupplierProvider);
    }

}
