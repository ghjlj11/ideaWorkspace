package com.ghj.springcloud.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalTime;
import java.util.concurrent.TimeUnit;

/**
 * @author guohuanjun1
 * @description: 测试接口
 * @date 2023/7/18 16:18
 */
@RestController
@RequestMapping("/test")
public class SentinelController {

    @RequestMapping("/a")
    public String testA(){
        return "=====test A";
    }

    @RequestMapping("/b")
    public String testB(){
        System.out.println("当前线程:" + Thread.currentThread().getName() + "当前时间:" + LocalTime.now().getSecond() + "-----testB");
        return "=====test B";
    }
}
