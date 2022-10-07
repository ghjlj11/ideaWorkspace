package com.ghj.priority;

import com.ghj.util.RabbitmqConnection;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;

import java.nio.charset.StandardCharsets;

/**
 * @author 86187
 * 测试优先级队列  消费者
 * info 6 会被优先接收
 */
public class PriConsumer {
    public static void main(String[] args) throws Exception {
        Channel channel = RabbitmqConnection.getChannel();
        DeliverCallback deliverCallback = (consumerTag, message) -> {
            System.out.println("消息已被接收：" + new String(message.getBody(), StandardCharsets.UTF_8));
        };
        channel.basicConsume("priority", true, deliverCallback, consumerTag -> {});
    }
}
