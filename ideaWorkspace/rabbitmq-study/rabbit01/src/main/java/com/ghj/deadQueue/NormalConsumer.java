package com.ghj.deadQueue;

import com.ghj.util.RabbitmqConnection;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 86187
 * 正常消费者
 */
public class NormalConsumer {
    private static final String NORMAL_EXCHANGE = "normal-exc";
    private static final String DEAD_EXCHANGE = "dead-exc";
    private static final String NORMAL_QUEUE = "normal-queue";
    private static final String DEAD_QUEUE = "dead-queue";

    public static void main(String[] args) throws Exception {
        Channel channel = RabbitmqConnection.getChannel();
        //声明普通交换机
        channel.exchangeDeclare(NORMAL_EXCHANGE, BuiltinExchangeType.DIRECT);
        //声明死信交换机
        channel.exchangeDeclare(DEAD_EXCHANGE, BuiltinExchangeType.DIRECT);

        Map<String, Object> arguments = new HashMap<>();
        //设置消息的ttl 一般是通过消息来设置消息的ttl， 而不是在队列统一设置ttl
        //arguments.put("x-message-ttl",10000);

        //绑定死信交换机
        arguments.put("x-dead-letter-exchange", DEAD_EXCHANGE);

        //设置该队列的死信routingKey
        arguments.put("x-dead-letter-routing-key","dead");

        //设置队列最大长度
        arguments.put("x-max-length", 6);

        //声明普通队列
        channel.queueDeclare(NORMAL_QUEUE, false, false, false, arguments);

        //绑定正常的交换机
        channel.queueBind(NORMAL_QUEUE, NORMAL_EXCHANGE, "normal");

        //声明死信队列
        channel.queueDeclare(DEAD_QUEUE, false, false, false, null);

        //死信队列绑定死信交换机
        channel.queueBind(DEAD_QUEUE, DEAD_EXCHANGE, "dead");

            DeliverCallback deliverCallback = (consumerTag, message) -> {
                String msg = new String(message.getBody(), StandardCharsets.UTF_8);
                if("info3".equals(msg)){
                    //拒绝接收消息， 参数是消息的tag， 以及是否重新入队列
                    channel.basicReject(message.getEnvelope().getDeliveryTag(), false);
                }else {
                    System.out.println("正常消费者收到消息:" + msg);
                    //确认
                    channel.basicAck(message.getEnvelope().getDeliveryTag(), false);
                }
            };
            channel.basicConsume(NORMAL_QUEUE, false, deliverCallback, consumerTag -> {});
    }
}
