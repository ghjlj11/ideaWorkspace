package com.ghj.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @author guohuanjun1
 * @description:
 * @date 2023/7/9 15:40
 */
@RestController
@RequestMapping("/consumer")
public class ConsumerController {

    @Value("${server-url.nacos-payment-server}")
    private String serverURL;

    @Resource
    RestTemplate restTemplate;

    @RequestMapping("/getPort")
    public String getPort(){
        return restTemplate.getForObject(serverURL + "/payment/getPort", String.class);
    }
}
