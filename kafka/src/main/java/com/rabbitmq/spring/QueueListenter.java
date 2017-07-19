package com.rabbitmq.spring;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.stereotype.Component;

@Component
public class QueueListenter implements MessageListener {

    public void onMessage(Message msg) {
        try{
            System.out.println("=========================");
            String body=new String(msg.getBody(),"UTF-8");
        }catch(Exception e){
            e.printStackTrace();
        }
    }

}
