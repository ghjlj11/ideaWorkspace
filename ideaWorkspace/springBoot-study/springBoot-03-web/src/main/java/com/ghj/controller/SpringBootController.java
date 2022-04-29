package com.ghj.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author 86187
 */

@Controller
public class SpringBootController {

    @RequestMapping("/hello")
    public String test1(Model model){
        model.addAttribute("msg","<h1>你在搞什么啊!</h1>");
        model.addAttribute("list", Arrays.asList("ghj","lj","lq"));
        return "hello";
    }
}
