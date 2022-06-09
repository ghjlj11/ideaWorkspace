package com.ghj.afterboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 86187
 */
@Controller
public class TestController {
    @RequestMapping("/hello")
    public String hello(){
        return "hello";
    }
}
