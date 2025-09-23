package com.careup.branch.branch.chat.controller;


import com.careup.branch.branch.chat.domain.ChatRoom;
import com.careup.branch.branch.chat.dto.ChatSendReqDto;
import com.careup.branch.branch.chat.dto.res.ChatMessageResDto;
import com.careup.branch.branch.chat.dto.res.ChatMyChatRoomResDto;
import com.careup.branch.branch.chat.service.ChatService;
import com.careup.branch.common.dto.CommonSuccessDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/chat")
public class ChatController {
    private final ChatService chatService;

    @GetMapping("/myChatRoom")
    public ResponseEntity<?> getMyChatRoom(){
        List<ChatMyChatRoomResDto> chatRoomList = chatService.getMyChatRoom();
        return new ResponseEntity<>(new CommonSuccessDto("", HttpStatus.CREATED.value(), "채팅방 생성 완료"), HttpStatus.CREATED);
    }


    //메시지 발행
    @MessageMapping("/{roomId}")
    public void sendMessage(@RequestBody ChatSendReqDto dto) {
        chatService.publish(dto);
    }


    //채팅 내역 조회
    @GetMapping("/history/{roomId}")
    public ResponseEntity<?> getChatHistory(@PathVariable Long roomId) {
        List<ChatMessageResDto> chatMessageDtos = chatService.getChatHistory(roomId);
        return new ResponseEntity<>(chatMessageDtos, HttpStatus.OK);
    }


}
