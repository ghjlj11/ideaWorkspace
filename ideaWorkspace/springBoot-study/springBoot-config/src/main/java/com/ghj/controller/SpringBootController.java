package com.ghj.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 86187
 */

@RestController
public class SpringBootController {
    @RequestMapping("/hello")
    public String test01(){
        return "hello";
    }
}
