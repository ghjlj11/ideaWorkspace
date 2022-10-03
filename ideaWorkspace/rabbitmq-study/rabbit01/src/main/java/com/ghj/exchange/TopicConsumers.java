package com.ghj.exchange;

import com.ghj.util.RabbitmqConnection;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author 86187
 * 测试 topic类型的交换机
 */
public class TopicConsumers {
    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                2,
                3,
                3,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(1),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy()
        );

        TopicConsumer consumer1 = new TopicConsumer(new String[]{"*.kk.*"}, "ghj");
        TopicConsumer consumer2 = new TopicConsumer(new String[]{"la.#"}, "lj");

        executor.execute(consumer1);
        executor.execute(consumer2);

    }
}
class TopicConsumer implements Runnable{
    private static final String EXCHANGE_NAME = "topic-exchange";
    private final String[] routingKeys;
    private final String name;
    public TopicConsumer(String[] routingKeys, String name){
        this.routingKeys = routingKeys;
        this.name = name;
    }
    @Override
    public void run() {
        try {
            Channel channel = RabbitmqConnection.getChannel();
            String queueName = channel.queueDeclare().getQueue();
            for (String routingKey : routingKeys) {
                channel.queueBind(queueName, EXCHANGE_NAME, routingKey);
            }
            DeliverCallback deliverCallback = (consumerTag, message) ->{
                System.out.println(name + "收到消息：" + new String(message.getBody(), StandardCharsets.UTF_8));
                //输出 routingKey
                System.out.println(name + "routingKey是：" + message.getEnvelope().getRoutingKey());
            };
            channel.basicConsume(queueName, true, deliverCallback, consumerTag -> {});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
