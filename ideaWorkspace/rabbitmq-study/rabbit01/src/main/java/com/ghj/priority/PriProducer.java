package com.ghj.priority;

import com.ghj.util.RabbitmqConnection;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 86187
 * 测试优先级队列
 */
public class PriProducer {
    public static void main(String[] args) throws Exception {
        Channel channel = RabbitmqConnection.getChannel();
        Map<String, Object> props = new HashMap<>();
        //设置队列优先级
        props.put("x-max-priority", 10);
        channel.queueDeclare("priority", false, false, false, props);
        channel.exchangeDeclare("priority-exc", BuiltinExchangeType.DIRECT);

        channel.queueBind("priority", "priority-exc", "priority");
        for (int i = 0; i < 10; i++) {
            String msg = "info" + i;
            if(i == 6){
                //设置消息的优先级
                AMQP.BasicProperties properties = new AMQP.BasicProperties().builder().priority(10).build();
                channel.basicPublish("priority-exc", "priority", properties, msg.getBytes(StandardCharsets.UTF_8));
            }
            else {
                channel.basicPublish("priority-exc", "priority", null, msg.getBytes(StandardCharsets.UTF_8));
            }
        }
    }
}
