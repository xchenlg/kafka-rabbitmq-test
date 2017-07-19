package com.rabbitmq.spring;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.stereotype.Component;

@Component
public class QueueListenter2 implements MessageListener {

    public void onMessage(Message msg) {
        try{
            String body=new String(msg.getBody(),"UTF-8");
            System.out.println("listener2=========="+body);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

}
