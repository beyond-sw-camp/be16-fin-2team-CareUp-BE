package com.careup.branch.branch.notification.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class SseAlarmService {

    private final SseEmitterRegistry emitterRegistry;

    public SseAlarmService(SseEmitterRegistry emitterRegistry) {
        this.emitterRegistry = emitterRegistry;
    }

    @KafkaListener(topics = "notification-topic", groupId = "sse-group")
    public void onMessage(String message) {
        // 이메일 기준으로 emitter 찾아서 전송
        try {
            emitterRegistry.getEmitter("someEmail").send(message);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
