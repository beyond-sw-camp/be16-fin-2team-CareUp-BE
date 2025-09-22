package com.careup.branch.chat.service;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaListenerService {
    @Value("spring.kafka.consumer")
    private String consumer;

    @Value("spring.kafka.topics")
    private String topics;

    @KafkaListener(topics = "test-topic", groupId = "test-topic-group-01")
    public void sendTest(ConsumerRecord<String, Object> record){
        System.out.println("send message : " + record.value());
    }
}
