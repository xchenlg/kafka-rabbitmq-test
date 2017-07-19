package com.rabbitmq.fabu_dingyue;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

public class ReceiveLogs1 {
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

        channel.exchangeDeclare(EXCHANGE_NAME, "fanout");

        // 产生一个随机的队列名称
//         String queueName = channel.queueDeclare().getQueue();
//         channel.queueBind(queueName, EXCHANGE_NAME, "");//对队列进行绑定

        channel.queueDeclare(QUEUE, DURABLE, false, false, null);  
        channel.queueBind(QUEUE, EXCHANGE_NAME,"");    // 对队列进行绑定

        System.out.println("ReceiveLogs1 Waiting for messages");
        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties,
                    byte[] body) throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println("ReceiveLogs1 Received '" + message + "'");
            }
        };
        channel.basicConsume(QUEUE, true, consumer);// 队列会自动删除
    }
}