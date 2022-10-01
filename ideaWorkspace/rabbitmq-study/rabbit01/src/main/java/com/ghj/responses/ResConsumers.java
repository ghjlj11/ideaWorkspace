package com.ghj.responses;

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
public class ResConsumers {

    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(2,
                3,
                5,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());

        ResConsumer consumer1 = new ResConsumer(1);
        ResConsumer consumer2 = new ResConsumer(30);

        executor.execute(consumer2);
        //executor.execute(consumer2);
    }
}

class ResConsumer implements Runnable {
    private static final String QUEUE_NAME = "ack-queue";
    private int sleepTime;
    public ResConsumer(int sleepTime){
        this.sleepTime = sleepTime;
    }
    @Override
    public void run() {
        try {
            Channel channel = RabbitmqConnection.getChannel();
            DeliverCallback deliverCallback = (consumerTag, message) -> {
                try {
                    Thread.currentThread().setName(sleepTime + "时间");
                    TimeUnit.SECONDS.sleep(sleepTime);
                    System.out.println(Thread.currentThread().getName() + "收到消息:" + new String(message.getBody()));
                    channel.basicAck(message.getEnvelope().getDeliveryTag(), false);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            };
            CancelCallback cancelCallback = (consumerTag) -> {
                System.out.println(Thread.currentThread().getName() + "接收失败回调");
            };
            channel.basicConsume(QUEUE_NAME, false, deliverCallback, cancelCallback);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
