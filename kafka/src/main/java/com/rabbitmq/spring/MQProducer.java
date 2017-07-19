package com.rabbitmq.spring;

public interface MQProducer {
    /**
     * 发送消息到指定队列
     * 
     * @param queueKey
     * @param object
     */
    public void sendDataToQueue(String queueKey, Object object);

    public void sendDataToQueueByDirect(String queue_key, Object object);
}
