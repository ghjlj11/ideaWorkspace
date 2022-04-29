package com.ghj.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 86187
 */

@Controller
public class Encoding {
    @RequestMapping("/e/t1/{method}")
    public String test1(@PathVariable("method") String method, Model model){
        System.out.println(method);
        if("add".equals(method)){
            return "hello";
        }
        return "form";
    }
}
