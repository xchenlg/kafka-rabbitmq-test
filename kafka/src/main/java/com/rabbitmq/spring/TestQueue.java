package com.rabbitmq.spring;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(value = SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring.xml", "classpath:amqp-direct.xml" })
public class TestQueue {

    public static final Logger logger = LoggerFactory.getLogger(TestQueue.class);

    @Autowired
    MQProducer mqProducer;

    final String queue_key = "spittle.alert.queue.1";

    @Test
    public void send() throws InterruptedException {
        Map<String, Object> msg = new HashMap<String, Object>();
        msg.put("data", "hello,rabbmitmq!");
        for (int i = 0; i < 20000; i++) {
            Thread.sleep(1000);
            mqProducer.sendDataToQueue(queue_key, msg);
        }
    }

    @Test
    public void sendDirect() throws InterruptedException {
        Map<String, Object> msg = new HashMap<String, Object>();
        msg.put("data", "hello,rabbmitmq!");
        for (int i = 0; i < 20000; i++) {
            Thread.sleep(1000);
            mqProducer.sendDataToQueueByDirect(queue_key, msg);
            mqProducer.sendDataToQueueByDirect("key5", msg);
        }
    }
    
    public static void main(String[] args) {
        
        
        File f = new File("C:\\Users\\chenlige\\Desktop\\pdf\\1.pdf");
        System.out.println(f.toURI().toString());
        System.out.println(f.toPath().toString());
        System.out.println(f.getAbsolutePath());
    }
}
