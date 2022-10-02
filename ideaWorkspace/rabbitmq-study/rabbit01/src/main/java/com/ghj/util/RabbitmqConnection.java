package com.ghj.util;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;


/**
 * @author 86187
 * rabbitmq获取channel
 */
public class RabbitmqConnection {
    public static final String QUEUE_NAME = "hello";
    public static Channel getChannel() throws Exception{
        Connection connection = MqConnection.getConnection();
        return connection.createChannel();
    }
}
