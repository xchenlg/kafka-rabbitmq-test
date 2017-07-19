package com.rabbitmq;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

public class Work2 {
    private static final String TASK_QUEUE_NAME = "task_queue";

    public static void main(String[] args) throws IOException, TimeoutException {
        final ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("192.168.113.132");
        factory.setUsername("test1");
        factory.setPassword("test1");
        factory.setPort(5672);
        Connection connection = factory.newConnection();
        final Channel channel = connection.createChannel();

        channel.queueDeclare(TASK_QUEUE_NAME, true, false, false, null);
        System.out.println("Worker1  Waiting for messages");

        // 每次从队列获取的数量
        channel.basicQos(1);

        final Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties,
                    byte[] body) throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println("Worker2  Received '" + message + "'");
//                try {
//                    throw new Exception();
//                    // doWork(message);
//                } catch (Exception e) {
//                    channel.abort();
//                } finally {
//                    System.out.println("Worker2 Done");
//                    channel.basicAck(envelope.getDeliveryTag(), false);
//                }
            }
        };
        boolean autoAck = false;
        // 消息消费完成确认
        channel.basicConsume(TASK_QUEUE_NAME, autoAck, consumer);
    }

    @SuppressWarnings("unused")
    private static void doWork(String task) {
        try {
            Thread.sleep(1000); // 暂停1秒钟
        } catch (InterruptedException _ignored) {
            Thread.currentThread().interrupt();
        }
    }
}