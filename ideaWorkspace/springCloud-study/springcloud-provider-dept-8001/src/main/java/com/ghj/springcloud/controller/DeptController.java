package com.ghj.springcloud.controller;

import com.ghj.springcloud.pojo.Dept;
import com.ghj.springcloud.service.DeptService;
import com.ghj.springcloud.service.DeptServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 86187
 */

@RestController
public class DeptController {

    @Autowired
    private DeptService deptService;

    @Autowired
    private DiscoveryClient client;

    @PostMapping("/dept/add")
    public String add(@RequestBody Dept dept){
        deptService.addDept(dept);
        return "ok";
    }

    @GetMapping("/dept/get/{id}")
    public Dept get(@PathVariable("id")Long id){
        return deptService.queryById(id);
    }

    @GetMapping("/dept/list")
    public List<Dept> getAll(){
        return deptService.queryAll();
    }

    @GetMapping("/dept/disc")
    public Object discovery(){
        List<String> services = client.getServices();
        List<ServiceInstance> instances = client.getInstances("SPRING-PROVIDER-DEPT");

        for (ServiceInstance instance : instances) {
            System.out.println(
                    instance.getHost() + "\t" +
                    instance.getPort() + "\t" +
                    instance.getUri() + "\t" +
                    instance.getInstanceId());
        }
        return this.client;
    }
}
