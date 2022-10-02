package com.ghj.publishAck;

import com.ghj.util.RabbitmqConnection;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConfirmCallback;

import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.TimeUnit;

/**
 * @author 86187
 */
public class SendMessageTest {
    private static final int MESSAGE_COUNT = 1000;
    public static void main(String[] args) throws Exception {
        //测试单个确认
        //testSingle()
        //测试批量确认
        //testMany();
        testSyn();
    }

    /**
     * 测试单个确认发布1000条消息所需要的时间
     * @throws Exception
     * 耗时34992ms
     */
    private static void testSingle() throws Exception {
        Channel channel = RabbitmqConnection.getChannel();
        //开启发布确认
        channel.confirmSelect();
        String queueName = UUID.randomUUID().toString();
        channel.queueDeclare(queueName, true, false, false, null);
        long begin = System.currentTimeMillis();
        for (int i = 0; i < MESSAGE_COUNT; i++) {
            String message = i + "";
            channel.basicPublish("", queueName, null, message.getBytes(StandardCharsets.UTF_8));
            //等待确认
            channel.waitForConfirms();
        }
        long end = System.currentTimeMillis();
        System.out.println("消息全部发送完成， 耗时" + (end - begin) + "ms");
    }
    /**
     * 测试批量确认发布1000条消息耗时
     * 每100条确认一次
     * 耗时719ms
     */

    public static void testMany () throws Exception {
        Channel channel = RabbitmqConnection.getChannel();
        int ack = 100;
        //开启发布确认
        channel.confirmSelect();
        String queueName = UUID.randomUUID().toString();
        channel.queueDeclare(queueName, true, false, false, null);
        long begin = System.currentTimeMillis();
        for (int i = 0; i < MESSAGE_COUNT; i++) {
            String message = i + "";
            channel.basicPublish("", queueName, null, message.getBytes(StandardCharsets.UTF_8));
            //批量等待确认
            if(i % ack == 0){
                channel.confirmSelect();
            }
        }
        long end = System.currentTimeMillis();
        System.out.println("消息全部发送完成， 耗时" + (end - begin) + "ms");
    }
    /**
     * 测试异步确认发送1000条消息的耗时
     * 耗时69ms
     */
    private static void testSyn() throws Exception {
        Channel channel = RabbitmqConnection.getChannel();
        //开启发布确认
        channel.confirmSelect();
        String queueName = UUID.randomUUID().toString();
        channel.queueDeclare(queueName, true, false, false, null);
        long begin = System.currentTimeMillis();
        //存储消息， 线程安全的集合， 并且可以通过序号删除， 支持高并发
        ConcurrentSkipListMap<Long, String> messages = new ConcurrentSkipListMap<>();
        //成功消息的确认, 回调函数
        ConfirmCallback ackCallback = (deliveryTag, multiple) -> {
            System.out.println("成功确认的消息:" + deliveryTag);
            if(messages.containsKey(deliveryTag)){
                System.out.println("删除消息：" + deliveryTag + messages);
                messages.remove(deliveryTag);
            }
            else {
                System.out.println("找不到此消息" + deliveryTag);
            }
        };

        //失败消息的确认, 回调函数
        ConfirmCallback nackCallback = (deliveryTag, multiple) -> {
            System.out.println("失败确认的消息:" + deliveryTag);
        };
        //开启异步确认  确认成功的消息  确认失败的消息
        channel.addConfirmListener(ackCallback,  nackCallback);

        for (int i = 0; i < 10; i++) {
            String message = i + "";
            //存放消息
            System.out.println("channel.getNextPublishSeqNo():" + channel.getNextPublishSeqNo());
            System.out.println("放入消息：" + i + messages);
            channel.basicPublish("", queueName, null, message.getBytes(StandardCharsets.UTF_8));
            messages.put(channel.getNextPublishSeqNo(), message);
        }
        long end = System.currentTimeMillis();
        System.out.println("消息全部发送完成， 耗时" + (end - begin) + "ms");
        TimeUnit.SECONDS.sleep(10);
        System.out.println(messages);
    }
}
