package com.ghj.springbootstudy01.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author 86187
 */

@Controller
@RequestMapping("/hello")
public class Controller01 {

    @ResponseBody()
    @RequestMapping("/xx")
    public String test1(){
        return "hello";
    }

}
