package com.ghj.rabbitmqspringboot.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author 86187
 */
@Slf4j
@RestController
@RequestMapping("/ttl")
public class SendMsgController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping("/send/{message}")
    public void send(@PathVariable String message){
        log.info("当前时间：{}，发送消息：{}", new Date(), message);
        //发送消息
        rabbitTemplate.convertAndSend("ex", "xa", "来自10s队列" + message);
        rabbitTemplate.convertAndSend("ex", "xb", "来自40s队列" + message);
    }

    @GetMapping("/sendMsg/{message}/{ttl}")
    public void sendMsg(@PathVariable String message, @PathVariable String ttl){
        log.info("当前时间：{}，发送消息：{}， ttl时间为：{}", new Date(), message, ttl);

        rabbitTemplate.convertAndSend("ex","xc","来自优化队列" + message, msg -> {
            //设置ttl时间
            msg.getMessageProperties().setExpiration(ttl);
            return msg;
        });
    }

    @GetMapping("/sendDelayMsg/{message}/{delayTime}")
    public void sendDelayMsg(@PathVariable String message, @PathVariable Integer delayTime){
        log.info("当前时间：{}，发送消息：{}， ttl时间为：{}", new Date(), message, delayTime);

        rabbitTemplate.convertAndSend("delay.exchange","delay.routingKey","来自插件优化队列" + message, msg -> {
            //设置ttl时间
            msg.getMessageProperties().setDelay(delayTime);
            return msg;
        });
    }
}
