package com.ghj.exchange;

import com.ghj.util.RabbitmqConnection;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;

import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * @author 86187
 */
public class TopicProducer {
    private static final String EXCHANGE_NAME = "topic-exchange";

    public static void main(String[] args) throws Exception {
        Channel channel = RabbitmqConnection.getChannel();
        //声明一个交换机， 类型是topic类型
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.TOPIC);
        Scanner scanner = new Scanner(System.in);
        int i = 0;
        while (scanner.hasNext()){
            String message = scanner.next();
            String routingKey;
            switch (i % 3){
                case 0:
                    //第一个获取消息
                    routingKey = "ll.kk.ww";
                    break;
                case 1:
                    //第二个获取消息
                    routingKey = "la.ss";
                    break;
                case 2:
                    //1 2 都获取消息
                    routingKey = "la.kk.zz";
                    break;
                default:
                    routingKey = "";
            }
            i ++;
            channel.basicPublish(EXCHANGE_NAME, routingKey, null, message.getBytes(StandardCharsets.UTF_8));
        }
    }
}
