package com.ghj.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * @author 86187
 */
@Controller
@RequestMapping("/ll")
public class HelloController {

    @RequestMapping("/hh/{method}")
    public String hello(@PathVariable("method")String method, Model model){
        System.out.println(method);
        if("add".equals(method)){
            model.addAttribute("msg", "Hello SpringMVC");
            return "hello";
        }
        else {
            return "form";
        }
    }
}
