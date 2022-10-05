package com.ghj.rabbitmqspringboot.consumers;

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
public class DeadQueueConsumer {

    @RabbitListener(queues = "qd")
    public void receiveD(Message message, Channel channel){
        String msg = new String(message.getBody(), StandardCharsets.UTF_8);
        log.info("当前时间：{}， 收到死信队列消息：{}", new Date(), msg);
    }

    @RabbitListener(queues = "delay.queue")
    public void receiveDelay(Message message, Channel channel){
        String msg = new String(message.getBody(), StandardCharsets.UTF_8);
        log.info("当前时间：{}， 收到延迟队列消息：{}", new Date(), msg);
    }
}
