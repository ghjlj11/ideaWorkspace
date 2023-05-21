package com.ghj.springcloud.controllrt;

import com.ghj.springcloud.pojo.Dept;
import com.ghj.springcloud.service.FeignService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @author 86187
 */
@RestController
public class MyController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyController.class);
    @Autowired
    FeignService feignService;

    @RequestMapping("/consumer/dept/get/{id}")
    public Dept get(@PathVariable("id") Long id){
        return feignService.queryById(id);
    }

    @RequestMapping("/consumer/dept/add")
    public String add( Dept dept){
        return feignService.addDept(dept);
    }
    @RequestMapping("/consumer/dept/list")
    public List list(){
        return feignService.queryAll();
    }
}
