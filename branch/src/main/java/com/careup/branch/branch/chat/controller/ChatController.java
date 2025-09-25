package com.careup.branch.branch.chat.controller;


import com.careup.branch.branch.chat.dto.ChatSendReqDto;
import com.careup.branch.branch.chat.dto.req.ChatMemberReqDto;
import com.careup.branch.branch.chat.dto.res.ChatMessageResDto;
import com.careup.branch.branch.chat.dto.res.ChatMyChatRoomResDto;
import com.careup.branch.branch.chat.service.ChatService;
import com.careup.branch.common.dto.CommonSuccessDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/chat")
public class ChatController {
    private final ChatService chatService;

    // 점주 아이디 - 본인 지역의 단체 채팅방 조회
    @GetMapping("/owner/GroupChatRoom")
    public ResponseEntity<?> joinChatRoom(@RequestParam String address){
        chatService.getGroupChatRoom(address);
        return new ResponseEntity<>(new CommonSuccessDto("", HttpStatus.ACCEPTED.value(), "단체 채팅방 가입 완료"), HttpStatus.ACCEPTED);
    }

    //채팅방 가입
    @GetMapping("/join/chatRoom")
    public ResponseEntity<?> joinChatRoom(@RequestParam Long chatRoomId){
        chatService.joinGroupChatRoom(chatRoomId);
        return new ResponseEntity<>(new CommonSuccessDto("", HttpStatus.ACCEPTED.value(), "채팅방 가입 완료"), HttpStatus.ACCEPTED);
    }

    //본인이 가입되어 있는 모든 채팅방 조회
    @GetMapping("/myChatRoom")
    public ResponseEntity<?> getMyChatRoom(){
//        List<ChatMyChatRoomResDto> chatRoomList = chatService.getMyChatRoom();
        return new ResponseEntity<>(new CommonSuccessDto("", HttpStatus.ACCEPTED.value(), "채팅방 조회 완료"), HttpStatus.ACCEPTED);
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
