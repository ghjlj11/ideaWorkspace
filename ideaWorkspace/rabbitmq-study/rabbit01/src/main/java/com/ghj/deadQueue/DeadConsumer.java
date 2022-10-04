package com.ghj.deadQueue;

import com.ghj.util.RabbitmqConnection;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;

import java.nio.charset.StandardCharsets;

/**
 * @author 86187
 * 死信消费者
 */
public class DeadConsumer {
    private static final String DEAD_QUEUE = "dead-queue";
    public static void main(String[] args) throws Exception {
        Channel channel = RabbitmqConnection.getChannel();
        DeliverCallback deliverCallback = (consumerTag, message) -> {
            System.out.println("死信消费者收到消息:" + new String(message.getBody(), StandardCharsets.UTF_8));
        };
        channel.basicConsume(DEAD_QUEUE, true, deliverCallback, consumerTag -> {});
    }
}
