package com.ghj.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;

/**
 * @author 86187
 */
@RestController
public class FirstController {
    @GetMapping("/first")
    public String add(){
        return "Get#add";
    }
    @PostMapping("/first")
    public String add1(){
        return "Post#add";
    }
    @PutMapping("/first")
    public String add2(){
        return "Put#add";
    }
}
