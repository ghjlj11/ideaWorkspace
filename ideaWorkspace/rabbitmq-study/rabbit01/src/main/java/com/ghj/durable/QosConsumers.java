package com.ghj.durable;

import com.ghj.util.RabbitmqConnection;
import com.rabbitmq.client.CancelCallback;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author 86187
 */
public class QosConsumers {
    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                2,
                3,
                10,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(2),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());
        QosConsumer consumer1 = new QosConsumer(10, 5);
        QosConsumer consumer2 = new QosConsumer(20, 3);
        executor.execute(consumer1);
        executor.execute(consumer2);
    }
}
class QosConsumer implements Runnable{
    private final int sleepTime;
    private final int prefetch;
    private static final String QUEUE_NAME = "dur-queue";
    public QosConsumer(int sleepTime, int prefetch){
        this.sleepTime = sleepTime;
        this.prefetch = prefetch;
    }
    @Override
    public void run() {
        try {
            Channel channel = RabbitmqConnection.getChannel();
            //默认值是0，也就是轮询分配
            channel.basicQos(prefetch);
            DeliverCallback deliverCallback = (consumerTag, message) -> {
                try {
                    Thread.currentThread().setName("睡觉时间为" + sleepTime + "的线程");
                    TimeUnit.SECONDS.sleep(sleepTime);
                    System.out.println(Thread.currentThread().getName() + "收到消息:" + new String(message.getBody()));
                    channel.basicAck(message.getEnvelope().getDeliveryTag(), false);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            };
            CancelCallback cancelCallback = (consumerTag) -> {
                System.out.println(Thread.currentThread().getName() + "接收消息失败回调");
            };
            channel.basicConsume(QUEUE_NAME, false, deliverCallback, cancelCallback);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

