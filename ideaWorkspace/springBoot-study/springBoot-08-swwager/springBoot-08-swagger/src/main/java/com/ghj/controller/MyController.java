package com.ghj.controller;

import com.ghj.pojo.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author 86187
 */
@Api(tags = "hello控制类")
@Controller
public class MyController {

    @ResponseBody
    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }

    @ApiOperation(value = "shb")
    @ResponseBody
    @PostMapping("/hello")
    public String hello2(){
        return "hello2";
    }
    @ApiOperation(value = "什么")
    @ResponseBody
    @PostMapping("/user")
    public User user(){
        return new User();
    }
    @ApiOperation(value = "获取")
    @ResponseBody
    @GetMapping("/user2")
    public User user2(User user){
        return user;
    }
}
