package com.ghj.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 86187
 */

@RestController
public class Controller01 {

    @RequestMapping("/aa")
    public void test01(){
        System.out.println("test01方法执行了======");
    }
}
