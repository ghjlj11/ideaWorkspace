package com.ghj.rabbitmqspringboot.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 86187
 * 高级确认 配置
 */
@Configuration
public class ConfirmConfig {
    public static final String QUEUE = "confirm.queue";
    public static final String EXCHANGE = "confirm.exchange";
    public static final String ROUTING_KEY = "kkk";
    /**
     *备份
     */
    public static final String BACK_EXCHANGE = "back.exchange";
    public static final String BACK_QUEUE = "back.queue";
    public static final String WARNING_QUEUE = "warning.queue";

    /**
     * 高级发布确认队列
     * @return
     */
    @Bean("confirmQueue")
    public Queue confirmQueue(){
        return new Queue(QUEUE);
    }
    /**
     * 备份队列
     * @return
     */
    @Bean("backQueue")
    public Queue backQueue(){
        return new Queue(BACK_QUEUE);
    }
    /**
     * 警告队列
     * @return
     */
    @Bean("warningQueue")
    public Queue warningQueue(){
        return new Queue(WARNING_QUEUE);
    }

    /**
     * 高级确认发布交换机
     * @return
     */
    @Bean("confirmExchange")
    public DirectExchange confirmExchange(){
        //绑定备份交换机
        return ExchangeBuilder.directExchange(EXCHANGE).alternate(BACK_EXCHANGE).build();
    }

    /**
     * 备份交换机
     * @return
     */
    @Bean("backExchange")
    public FanoutExchange backExchange(){
        return ExchangeBuilder.fanoutExchange(BACK_EXCHANGE).build();
    }


    @Bean
    public Binding confirmQueueBindEx(@Qualifier("confirmQueue") Queue queue, @Qualifier("confirmExchange") DirectExchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY);
    }

    /**
     * 备份与警告队列绑定备份交换机
     * @param backQueue
     * @param backExchange
     * @return
     */
    @Bean
    public Binding backQueueBind(@Qualifier("backQueue") Queue backQueue, @Qualifier("backExchange") FanoutExchange backExchange){
        return BindingBuilder.bind(backQueue).to(backExchange);
    }
    @Bean
    public Binding warningQueueBind(@Qualifier("warningQueue") Queue warningQueue, @Qualifier("backExchange") FanoutExchange backExchange){
        return BindingBuilder.bind(warningQueue).to(backExchange);
    }

}
