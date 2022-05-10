package com.ghj.springcloud.controller;

import com.ghj.springcloud.pojo.Dept;
import com.ghj.springcloud.service.DeptServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 86187
 */

@RestController
public class DeptController {

    @Autowired
    private DeptServiceImpl deptService;

    @PostMapping("/dept/add")
    public String add(Dept dept){
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
}
