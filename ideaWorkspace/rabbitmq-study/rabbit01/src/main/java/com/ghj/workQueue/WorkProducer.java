package com.ghj.workQueue;

import com.ghj.util.RabbitmqConnection;
import com.rabbitmq.client.Channel;

import java.util.Scanner;

/**
 * @author 86187
 * 工作队列的生产者
 */
public class WorkProducer {
    public static void main(String[] args) throws Exception {
        Channel channel = RabbitmqConnection.getChannel();
        channel.queueDeclare(RabbitmqConnection.QUEUE_NAME, false, false, false, null);

        //持续扫描传入队列消息
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()){
            String message = scanner.next();
            channel.basicPublish("", RabbitmqConnection.QUEUE_NAME, null, message.getBytes());
        }
    }
}
