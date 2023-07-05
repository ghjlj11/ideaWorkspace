package com.ghj.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 86187
 */
@RestController
@RefreshScope
public class ConfigController {
    @Value("${server.port}")
    private Integer port;
    @Value("${config.info}")
    String info;
    @RequestMapping("/info")
    public String info(){
        return port + ":" + info;
    }
}
