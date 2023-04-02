package com.ghj.helloWorld;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.nio.charset.StandardCharsets;


/**
 * @author 86187
 */
public class Producer {
    private static final String QUEUE_NAME = "hello";

    public static void main(String[] args) throws Exception{
        //创建连接工厂，设置参数
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("ghjlj.cn");
        factory.setUsername("admin");
        factory.setPassword("admin");

        //创建链接
        Connection connection = factory.newConnection();
        //创建信道
        Channel channel = connection.createChannel();

        /**
         * 生成一个队列
         * 1 队列名称
         * 2 队列的消息是否需要持久化 ， 默认消息存在内存， 非持久化
         * 3 该队列是否只供一个消费者 ，是否消息共享 ， true 多个消费者
         * 4 是否自动删除
         * 5 其他参数
         */
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        String message = "hello rabbitmq";

        /**
         * 发送一个消费
         * 1 发送到哪个交换机
         * 2 路由的key是哪个  本次队列名称
         * 3 其他参数
         * 4 发送的消息体
         */

        channel.basicPublish("", QUEUE_NAME, null, message.getBytes(StandardCharsets.UTF_8));

        System.out.println("消息发送完成");
    }
}
