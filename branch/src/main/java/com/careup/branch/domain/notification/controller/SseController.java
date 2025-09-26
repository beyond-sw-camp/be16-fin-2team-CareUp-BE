package com.careup.branch.domain.notification.controller;

import com.careup.branch.domain.notification.service.SseEmitterRegistry;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/sse")
public class SseController {
    private final SseEmitterRegistry sseEmitterRegistry;

    @GetMapping("/connect")
    public SseEmitter subscribe() {
        SseEmitter sseEmitter = new SseEmitter(14400*60*1000L); // 10일정도 emitter 유효기간 설정 // Todo - 시간 줄이기
        //todo - security 연결
//        String email = SecurityContextHolder.getContext().getAuthentication().getName();
//        sseEmitterRegistry.addSseEmitter(email, sseEmitter);
        try {
            sseEmitter.send(SseEmitter.event().name("connect").data("연결완료"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return sseEmitter;
    }

    @GetMapping("/disconnect")
    public void unSubscribe() {
        //todo : security 연결
//        String email = SecurityContextHolder.getContext().getAuthentication().getName();
//        sseEmitterRegistry.removeSseEmitter(email);
    }
}
