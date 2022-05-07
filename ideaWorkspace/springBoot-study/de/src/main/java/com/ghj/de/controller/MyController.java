package com.ghj.de.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 86187
 */
@Controller
public class MyController {
    @RequestMapping("/hello")
    public String hello(){
        return "hello";
    }
}
