package com.ghj.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 86187
 */

@Controller
public class HelloController2 {


    @RequestMapping("/h1/t1")
    public String test1(Model model){
        return "redirect:/WEB-INF/jsp/index.jsp";
    }
}
