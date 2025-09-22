package com.careup.branch.chat.controller;


import com.careup.branch.chat.dto.ChatSendReqDto;
import com.careup.branch.chat.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor
public class ChatController {
    private final ChatService chatService;

    //메시지 발행
    @MessageMapping("/{roomId}")
    public void sendMessage(@RequestBody ChatSendReqDto dto) {
        chatService.publish(dto);
    }

}
