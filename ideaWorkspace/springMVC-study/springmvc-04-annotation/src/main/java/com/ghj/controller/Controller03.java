package com.ghj.controller;

import com.ghj.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author 86187
 */

@Controller
public class Controller03 {

    @RequestMapping("/hh/kk/{names}")
    public String test01(@PathVariable("names") String names){
        System.out.println(names);
        if("add".equals(names)){
            return "hello";
        }
        else {
            return "form";
        }
    }
}
