package com.ghj.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.ghj.springcloud.exception.CommonSentinelExceptionHandler;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.util.concurrent.TimeUnit;

/**
 * @Description:
 * @author guohuanjun
 * @date 2023/7/19  11:50
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

    @RequestMapping("/d")
    public String testD(){
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "=====test D";
    }

    @RequestMapping("/e/{i}")
    public String testE( @PathVariable Integer i){
        int h = 10 / i;
        return "=====test E..." + h;
    }
    @GetMapping("/f")
    @SentinelResource(value = "testF",  blockHandler = "dealHotKey")
    public String testF(@RequestParam(value = "a", required = false) Integer a,
                        @RequestParam(value = "b", required = false) Integer b){
        return "TestF......." + a + ":" + b;
    }

    public String dealHotKey(Integer a, Integer b, BlockException exception){
        return "dealHotKey......";
    }

    /**
     * 规则持久化
     * @return
     */
    @RequestMapping("/dataSource")
    @SentinelResource("dataSource")
    public String testDataSource(){
        return "sentinel 持久化规则";
    }
}
