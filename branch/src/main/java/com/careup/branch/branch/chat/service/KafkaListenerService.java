package com.careup.branch.branch.chat.service;

import com.careup.branch.branch.chat.domain.ChatMessage;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KafkaListenerService {
    private final ChatService chatService;
    @Value("spring.kafka.consumer")
    private String consumer;

    @Value("spring.kafka.topics")
    private String topics;

    @KafkaListener(topics = "test-topic", groupId = "test-topic-group-01")
    public void sendTest(ConsumerRecord<String, Object> record){
        System.out.println("send message : " + record.value());
        String message = record.value().toString();
//        chatService.publish(message);
    }
}
