package com.ghj.responses;

import com.ghj.util.RabbitmqConnection;
import com.rabbitmq.client.Channel;

import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * @author 86187
 */
public class ResProducer {
    private static final String QUEUE_NAME = "ack-queue";
    public static void main(String[] args) throws Exception {
        Channel channel = RabbitmqConnection.getChannel();
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        Scanner scanner = new Scanner(System.in);
        System.out.println("生产者发送消息:");
        while (scanner.hasNext()){
            String next = scanner.next();
            channel.basicPublish("", QUEUE_NAME, null, next.getBytes(StandardCharsets.UTF_8));
        }
    }
}
