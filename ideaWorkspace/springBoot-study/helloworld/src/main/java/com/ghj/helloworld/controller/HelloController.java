package com.ghj.helloworld.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 86187
 */

@RestController
public class HelloController {

    @RequestMapping("/hello")
    public String test1(){
        return "Hello SpringBoot";
    }
}
