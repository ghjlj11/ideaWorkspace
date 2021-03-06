package com.ghj.springcloud.controllrt;

import com.ghj.springcloud.pojo.Dept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @author 86187
 */

@RestController
public class MyController {


    @Autowired
    RestTemplate restTemplate;
    /**
     * Ribbon这里应该需要的是一个服务名， 因为如果写的是http://localhost:8001那么就只会直接访问服务端，
     * 都不会走注册中心， 这里需要写一个服务的名字， 这样就可以访问注册中心。
     */
    private static final String PREFIX = "http://SPRING-PROVIDER-DEPT";

    @RequestMapping("/consumer/dept/get/{id}")
    public Dept get(@PathVariable("id") Long id){
        return restTemplate.getForObject(PREFIX + "/dept/get/" + id, Dept.class);
    }

    @RequestMapping("/consumer/dept/add")
    public String add( Dept dept){
        return restTemplate.postForObject(PREFIX + "/dept/add", dept, String.class);
    }
    @RequestMapping("/consumer/dept/list")
    public List list(){
        return restTemplate.getForObject(PREFIX + "/dept/list",List.class);
    }
}
