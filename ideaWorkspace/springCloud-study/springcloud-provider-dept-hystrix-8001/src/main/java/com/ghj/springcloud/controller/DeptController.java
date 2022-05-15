package com.ghj.springcloud.controller;

import com.ghj.springcloud.pojo.Dept;
import com.ghj.springcloud.service.DeptService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;
import org.yaml.snakeyaml.events.Event;

import java.util.List;

/**
 * @author 86187
 */

@RestController
public class DeptController {

    @Autowired
    private DeptService deptService;

    @Value("${server.port}")
    int port;


    @HystrixCommand(fallbackMethod = "hystrixGet")
    @GetMapping("/dept/get/{id}")
    public Dept get(@PathVariable("id")Long id){
        Dept dept = deptService.queryById(id);
        if(dept == null){
            throw new RuntimeException("id is null not found");
        }
        return dept;
    }

    private Dept hystrixGet(@PathVariable("id") Long id){
        return new Dept()
                .setDept_no(id)
                .setD_name("id=>" + id + "is not found")
                .setDb_source("no database in MySQL");
    }

    @GetMapping("/dept/list")
    public List list(){
        return deptService.queryAll();
    }

    @GetMapping("dept/port")
    public int port(){
        return this.port;
    }

}
