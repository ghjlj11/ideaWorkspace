package com.ghj.springcloud.controller;

import com.ghj.springcloud.dto.CommonResult;
import com.ghj.springcloud.pojo.MyDept;
import com.ghj.springcloud.service.SentinelService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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


    @Resource
    SentinelService sentinelService;

    @RequestMapping("/getDept")
    public CommonResult<MyDept> getPort(@RequestParam(value = "id", required = false) Long id){
        return sentinelService.getDept(id);
    }
}
