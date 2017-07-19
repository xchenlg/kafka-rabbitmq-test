package com.rabbitmq.fabu_dingyue;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class EmitLog {
    private static final String EXCHANGE_NAME = "logs";
    private final static String QUEUE = "temp_fanout";  
    private final static boolean DURABLE = false; 
    
    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("192.168.113.132");
        factory.setUsername("test1");
        factory.setPassword("test1");
        factory.setPort(5672);
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare(EXCHANGE_NAME, "fanout");// fanout表示分发，所有的消费者得到同样的队列信息
        channel.queueDeclare(QUEUE, DURABLE, false, false, null);  

        // 分发信息
        for (int i = 0; i < 5; i++) {
            String message = "Hello World" + i;
            channel.basicPublish(EXCHANGE_NAME, QUEUE, null, message.getBytes());
            System.out.println("EmitLog Sent '" + message + "'");
        }
        channel.close();
        connection.close();
    }
}