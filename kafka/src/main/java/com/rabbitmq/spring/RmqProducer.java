package com.rabbitmq.spring;

import javax.annotation.Resource;

import org.springframework.amqp.rabbit.core.RabbitTemplate;

public class RmqProducer {

    @Resource
    private RabbitTemplate rabbitTemplate;

    /**
     * 发送信息
     * 
     * @param msg
     */
    public void sendMessage(RabbitMessage msg) {
        try {
            System.out.println(rabbitTemplate.getConnectionFactory().getHost());
            System.out.println(rabbitTemplate.getConnectionFactory().getPort());
            // 发送信息
            rabbitTemplate.convertAndSend(msg.getExchange(), msg.getRouteKey(), msg);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
