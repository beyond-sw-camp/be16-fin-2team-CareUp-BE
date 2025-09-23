package com.careup.branch.chat.service;

import com.careup.branch.chat.dto.ChatSendReqDto;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ChatService {
    private final SimpMessagingTemplate messagingTemplate;

    public void publish(ChatSendReqDto dto) {
        messagingTemplate.convertAndSend("/topic/" + dto.getRoomId(), dto);
    }
}
