package com.ghj.exchange;

import com.ghj.util.RabbitmqConnection;
import com.rabbitmq.client.Channel;

import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * @author 86187
 */
public class ExcProducer {
    private static final String EXCHANGE_NAME = "lj-exchange";

    public static void main(String[] args) throws Exception {
        Channel channel = RabbitmqConnection.getChannel();
        //声明一个交换机， 类型是fanout类型
        channel.exchangeDeclare(EXCHANGE_NAME, "direct");
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()){
            String message = scanner.next();
            channel.basicPublish(EXCHANGE_NAME, "22", null, message.getBytes(StandardCharsets.UTF_8));
        }
    }
}
