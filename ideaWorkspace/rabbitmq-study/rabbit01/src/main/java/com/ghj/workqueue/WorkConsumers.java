package com.ghj.workqueue;

import com.ghj.util.RabbitmqConnection;
import com.rabbitmq.client.CancelCallback;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;

import java.util.concurrent.*;

/**
 * @author 86187
 */
public class WorkConsumers {
    public static void main(String[] args) {
        Consumers consumers1 = new Consumers();
        Consumers consumers2 = new Consumers();
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(2,
                5,
                3,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(5),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());
        threadPool.execute(consumers1);
        threadPool.execute(consumers2);
    }

    static class Consumers implements Runnable {
        @Override
        public void run() {
            try {
                Channel channel = RabbitmqConnection.getChannel();
                DeliverCallback deliverCallback = (consumerTag, message) -> {
                    System.out.println(Thread.currentThread().getName() + "收到消息：" + new String(message.getBody()));
                };
                CancelCallback cancelCallback = (consumerTag) -> {
                    System.out.println(Thread.currentThread().getName() + "接收消息失败!");
                };
                channel.basicConsume(RabbitmqConnection.QUEUE_NAME, true, deliverCallback, cancelCallback);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
