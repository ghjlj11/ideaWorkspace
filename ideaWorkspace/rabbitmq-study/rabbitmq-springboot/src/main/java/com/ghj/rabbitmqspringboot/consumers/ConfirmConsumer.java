package com.ghj.rabbitmqspringboot.consumers;

import com.ghj.rabbitmqspringboot.config.ConfirmConfig;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Date;

/**
 * @author 86187
 */
@Slf4j
@Component
public class ConfirmConsumer {

    @RabbitListener(queues = ConfirmConfig.QUEUE)
    public void receiveConfirm(Message message, Channel channel){
        String msg = new String(message.getBody(), StandardCharsets.UTF_8);
        log.info("当前时间：{}， 收到高级确认队列消息：{}", new Date(), msg);
    }


    @RabbitListener(queues = ConfirmConfig.WARNING_QUEUE)
    public void receiveWarning(Message message, Channel channel){
        String msg = new String(message.getBody(), StandardCharsets.UTF_8);
        log.info("当前时间：{}， 收到报警队列消息：{}", new Date(), msg);
    }

}
