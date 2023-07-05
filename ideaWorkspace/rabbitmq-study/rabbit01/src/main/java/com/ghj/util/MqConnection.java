package com.ghj.util;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author 86187
 */
public class MqConnection {
    private static volatile Connection connection;
    private static final ReentrantLock LOCK = new ReentrantLock();
    private MqConnection(){}
    public static Connection getConnection(){
        if(connection == null){
            LOCK.lock();
            try {
                if(connection == null){
                    ConnectionFactory factory = new ConnectionFactory();
                    factory.setHost("ghjlj.cn");
                    factory.setUsername("admin");
                    factory.setPassword("admin");
                    connection = factory.newConnection();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                LOCK.unlock();
            }
        }
        return connection;
    }
}
