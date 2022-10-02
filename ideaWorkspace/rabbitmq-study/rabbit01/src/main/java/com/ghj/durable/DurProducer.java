package com.ghj.durable;

import com.ghj.util.RabbitmqConnection;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.MessageProperties;

import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * @author 86187
 */
public class DurProducer {
    private static final String QUEUE_NAME = "dur-queue";
    public static void main(String[] args) throws Exception {
        Channel channel = RabbitmqConnection.getChannel();
        channel.queueDeclare(QUEUE_NAME, true, false, false, null);
        Scanner scanner = new Scanner(System.in);
        System.out.println("生产者发送消息:");
        while (scanner.hasNext()){
            String next = scanner.next();
            channel.basicPublish("", QUEUE_NAME, MessageProperties.PERSISTENT_TEXT_PLAIN, next.getBytes(StandardCharsets.UTF_8));
        }
    }
}
