package com.rabbitmq.spring;

import org.apache.log4j.Logger;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MQProducerImpl implements MQProducer {

    @Autowired
    private AmqpTemplate amqpTemplate;

    private final static Logger LOGGER = Logger.getLogger(MQProducerImpl.class);

    public void sendDataToQueue(String queueKey, Object object) {
        try {
            amqpTemplate.convertAndSend(object);
            System.out.println(object);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }

    }
    
    
    public void sendDataToQueueByDirect(String queueKey, Object object) {
        try {
            amqpTemplate.convertAndSend(queueKey,object);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }

    }
}
