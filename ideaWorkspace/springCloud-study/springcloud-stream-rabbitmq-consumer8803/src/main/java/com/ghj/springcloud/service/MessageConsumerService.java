package com.ghj.springcloud.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.Message;

/**
 * @author guohuanjun1
 * @date 2023/7/5
 */
@EnableBinding(Sink.class)
public class MessageConsumerService {

    @Value("${server.port}")
    private Integer port;

    @StreamListener(Sink.INPUT)
    public void input(Message<String> message){
        System.out.println("端口:" + port + ",消费者收到消息" + message.getPayload() + "\t");
    }
}
