package com.ghj.deadQueue;

import com.ghj.util.RabbitmqConnection;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;

import java.nio.charset.StandardCharsets;

/**
 * @author 86187
 */
public class DeadProducer {
    private static final String NORMAL_EXCHANGE = "normal-exc";
    public static void main(String[] args) throws Exception {
        Channel channel = RabbitmqConnection.getChannel();
        //设置消息的ttl
        AMQP.BasicProperties properties = new AMQP.BasicProperties()
                .builder()
                .expiration("10000")
                .build();
        for (int i = 0; i < 10; i++) {
            String message = "info" + i;
            channel.basicPublish(NORMAL_EXCHANGE, "normal", null, message.getBytes(StandardCharsets.UTF_8));
        }
    }
}
