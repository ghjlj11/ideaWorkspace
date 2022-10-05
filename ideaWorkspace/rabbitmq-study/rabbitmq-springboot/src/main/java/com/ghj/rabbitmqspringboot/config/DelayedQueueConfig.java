package com.ghj.rabbitmqspringboot.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 86187
 */
@Configuration
public class DelayedQueueConfig {

    private static final String EXCHANGE_NAME = "delay.exchange";
    private static final String QUEUE_NAME = "delay.queue";
    private static final String ROUTING_KEY = "delay.routingKey";

    /**
     * 队列
     * @return
     */
    @Bean
    public Queue delayQueue(){
        return new Queue(QUEUE_NAME);
    }

    /**
     * 交换机设置
     * @return
     */
    @Bean
    public CustomExchange delayExchange(){
        Map<String, Object> arguments = new HashMap<>();
        //设置交换机类型
        arguments.put("x-delayed-type", "direct");
        return new CustomExchange(EXCHANGE_NAME,"x-delayed-message",  false, false, arguments);
    }

    /**
     * 绑定
     * @param delayQueue
     * @param delayExchange
     * @return
     */
    @Bean
    public Binding queueBindDelayExchange(@Qualifier("delayQueue") Queue delayQueue, @Qualifier("delayExchange") CustomExchange delayExchange){
        return BindingBuilder.bind(delayQueue).to(delayExchange).with(ROUTING_KEY).noargs();
    }
}
