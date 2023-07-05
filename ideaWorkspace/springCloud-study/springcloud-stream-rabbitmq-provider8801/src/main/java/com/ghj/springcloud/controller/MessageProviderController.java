package com.ghj.springcloud.controller;

import com.ghj.springcloud.service.MessageProvider;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author guohuanjun1
 * @date 2023/7/5
 */
@RestController
@RequestMapping("/message")
public class MessageProviderController {

    @Resource
    MessageProvider messageProvider;

    @RequestMapping("/send")
    public String sendMessage(){
        return messageProvider.send();
    }
}
