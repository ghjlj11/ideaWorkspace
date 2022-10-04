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
public class TtlQueueConfig {
    /**
     * 名称
     */
    private static final String QUEUE_A = "qa";
    private static final String QUEUE_B = "qb";
    private static final String DEAD_QUEUE_D = "qd";
    private static final String EXCHANGE_X = "ex";
    private static final String EXCHANGE_Y = "ey";

    /**
     *声明交换机
     */
    @Bean("xExchange")
    public DirectExchange xExchange(){
        return new DirectExchange(EXCHANGE_X);
    }
    @Bean("yExchange")
    public DirectExchange yExchange(){
        return new DirectExchange(EXCHANGE_Y);
    }

    /**
     * 声明队列
     */
    @Bean("queueA")
    public Queue queueA(){
        Map<String, Object> args = new HashMap<>(3);
        //设置死信交换机
        args.put("x-dead-letter-exchange", EXCHANGE_Y);
        //设置死信routingKey
        args.put("x-dead-letter-routing-key", "da");
        //设置ttl时间
        args.put("x-message-ttl", 10000);
        return QueueBuilder.nonDurable(QUEUE_A).withArguments(args).build();
    }

    @Bean("queueB")
    public Queue queueB(){
        Map<String, Object> args = new HashMap<>(3);
        //设置死信交换机
        args.put("x-dead-letter-exchange", EXCHANGE_Y);
        //设置死信routingKey
        args.put("x-dead-letter-routing-key", "da");
        //设置ttl时间
        args.put("x-message-ttl", 40000);
        return QueueBuilder.nonDurable(QUEUE_B).withArguments(args).build();
    }

    @Bean("queueD")
    public Queue queueD(){
        return QueueBuilder.nonDurable(DEAD_QUEUE_D).build();
    }

    /**
     * 绑定
     */
    @Bean
    public Binding queueABindX(@Qualifier("queueA") Queue queueA, @Qualifier("xExchange") DirectExchange exchangeX){
        return BindingBuilder.bind(queueA).to(exchangeX).with("xa");
    }
    @Bean
    public Binding queueBBindX(@Qualifier("queueB") Queue queueB, @Qualifier("xExchange") DirectExchange exchangeX){
        return BindingBuilder.bind(queueB).to(exchangeX).with("xb");
    }
    @Bean
    public Binding queueDBindY(@Qualifier("queueD") Queue queueD, @Qualifier("yExchange") DirectExchange exchangeY){
        //这里的routingKey就是 qa，qb网死信交换机发消息的routingKey
        return BindingBuilder.bind(queueD).to(exchangeY).with("da");
    }
}
