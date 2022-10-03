package com.ghj.exchange;

import com.ghj.util.RabbitmqConnection;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author 86187
 * 测试 fanout， direct类型的交换机
 */
public class ExcConsumers {

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
        ExcConsumer consumer1 = new ExcConsumer("ghj");
        ExcConsumer consumer2 = new ExcConsumer("lj");
        executor.execute(consumer1);
        executor.execute(consumer2);
    }
}
class ExcConsumer implements Runnable{
    private static final String EXCHANGE_NAME = "lj-exchange";
    private String name;
    public ExcConsumer(String name){
        this.name = name;
    }
    @Override
    public void run() {
        try {
            Channel channel = RabbitmqConnection.getChannel();
            //声明一个临时的队列， 当没有消费者链接就会删除此队列。
            String queueName = channel.queueDeclare().getQueue();
            //队列绑定交换机
            channel.queueBind(queueName, EXCHANGE_NAME, "22");
            channel.queueBind(queueName, EXCHANGE_NAME, "33");

            DeliverCallback deliverCallback = (consumerTag, message) ->{
                System.out.println(name + "成功收到消息：" + new String(message.getBody()));
            };
            channel.basicConsume(queueName, true, deliverCallback, (consumerTag) -> {});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
