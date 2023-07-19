package com.ghj.springcloud.controller;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
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
}
