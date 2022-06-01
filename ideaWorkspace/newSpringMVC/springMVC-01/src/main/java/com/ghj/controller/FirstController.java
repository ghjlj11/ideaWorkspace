package com.ghj.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author 86187
 */
@Controller
public class FirstController {
    @ResponseBody
    @RequestMapping("/first")
    public String add(){
        return "add";
    }
}
