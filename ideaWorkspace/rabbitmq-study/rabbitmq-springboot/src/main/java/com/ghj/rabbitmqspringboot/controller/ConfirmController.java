package com.ghj.rabbitmqspringboot.controller;

import com.ghj.rabbitmqspringboot.config.ConfirmConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.ReturnedMessage;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.StandardCharsets;
import java.util.Date;

/**
 * @author 86187
 * 高级确认 controller
 */
@Slf4j
@RestController
@RequestMapping("/con")
public class ConfirmController {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @GetMapping("/send/{message}")
    public void confirmSend(@PathVariable String message){
        log.info("当前时间：{}，高级确认发出消息：{}", new Date(), message);
        //设置 correlationData， 在回调接口可以使用到
        CorrelationData correlationData = new CorrelationData("1");
        Message msg = new Message(message.getBytes(StandardCharsets.UTF_8));
        ReturnedMessage returnedMessage = new ReturnedMessage(msg, 2, "xxx", ConfirmConfig.EXCHANGE, ConfirmConfig.ROUTING_KEY);
        correlationData.setReturned(returnedMessage);
        rabbitTemplate.convertAndSend(ConfirmConfig.EXCHANGE, ConfirmConfig.ROUTING_KEY, message, correlationData);
        rabbitTemplate.convertAndSend(ConfirmConfig.EXCHANGE, ConfirmConfig.ROUTING_KEY + "11", message + "交换机正确，key不正确", correlationData);
        rabbitTemplate.convertAndSend(ConfirmConfig.EXCHANGE + "11", ConfirmConfig.ROUTING_KEY + "11", message + "交换机不正确，key不正确", correlationData);
    }
}
