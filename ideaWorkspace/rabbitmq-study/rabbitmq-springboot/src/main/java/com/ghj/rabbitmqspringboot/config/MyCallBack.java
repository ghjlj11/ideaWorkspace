package com.ghj.rabbitmqspringboot.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ReturnedMessage;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.nio.charset.StandardCharsets;
import java.util.Date;

/**
 * @author 86187
 * 定义自己的 交换机确认接收消息后的回调
 */
@Slf4j
@Component
public class MyCallBack implements RabbitTemplate.ConfirmCallback, RabbitTemplate.ReturnsCallback{

    @Autowired
    RabbitTemplate rabbitTemplate;

    /**
     * 初始化， 将rabbitTemplate类里的 confirmCallback 替换为我们自己写的
     * 该注解 会让这个方法在bean组策完成之后执行这个方法
     */
    @PostConstruct
    public void init(){
        rabbitTemplate.setConfirmCallback(this);
        rabbitTemplate.setReturnsCallback(this);
    }
    /**
     *
     * @param correlationData  消息的id以及内容， 并不是每个消息都有， 需要在发消息的时候配置
     * @param ack  交换机是否成功收到消息
     * @param cause  失败的原因
     */
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        String id = null;
        String msg = null;
        if(correlationData != null){
            id = correlationData.getId();
            byte[] body = correlationData.getReturned().getMessage().getBody();
            msg = new String(body, StandardCharsets.UTF_8);
        }
        if(ack){
            log.info("交换机收到消息id为：{}, 消息是：{}, 当前时间为：{}", id, msg, new Date());
        }else {
            log.info("交换机没有收到消息id为：{}, 消息是：{}, 当前时间为：{}", id, msg, new Date());
            log.info("失败的原因是：{}", cause);
        }
    }

    /**
     * 当消息没有成功发送到队列时候， 会触发这个方法
     * @param returned 消息的一些信息，这里的ReturnedMessage与交换机未接受的ReturnedMessage不一样， 内容不是一样的
     */
    @Override
    public void returnedMessage(ReturnedMessage returned) {
        log.info("消息：{}，没有被队列接收， 交换机是：{}， 路由key是：{}， 原因是：{}， code：{}",
                new String(returned.getMessage().getBody(), StandardCharsets.UTF_8), returned.getExchange(), returned.getRoutingKey(),
                returned.getReplyText(), returned.getReplyCode());
    }
}
