package com.ghj.helloWorld;

import com.rabbitmq.client.*;


/**
 * @author 86187
 */
public class Consumer {

    private static final String QUEUE_NAME = "hello";

    public static void main(String[] args) throws Exception {
        //创建连接工厂，设置参数
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("47.102.117.225");
        factory.setUsername("admin");
        factory.setPassword("admin");

        //创建链接
        Connection connection = factory.newConnection();
        //创建信道
        Channel channel = connection.createChannel();

        /**
         * 消费者消费消息
         * 1 队列的名称， 消费哪个队列
         * 2 消费成功之后是否需要自动答应， false代表手动 ， true代表自动
         * 3 消费者消费成功的回调函数
         * 4 消费者消费失败的回调函数
         */

        DeliverCallback deliverCallback = (consumerTag, message) -> {
            //message.getBody()获取的是消息体的字节数组
            System.out.println(new String(message.getBody()));
        };
        CancelCallback cancelCallback = (consumerTag) -> {
            System.out.println("消费者消费失败");
        };
        channel.basicConsume(QUEUE_NAME, true, deliverCallback, cancelCallback);
    }
}
