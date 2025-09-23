package com.careup.branch.branch.chat.service;

import com.careup.branch.branch.chat.domain.ChatRoom;
import com.careup.branch.branch.chat.dto.ChatSendReqDto;
import com.careup.branch.branch.chat.dto.res.ChatMessageResDto;
import com.careup.branch.branch.chat.dto.res.ChatMyChatRoomResDto;
import com.careup.branch.branch.chat.repository.ChatRoomRepository;
import com.careup.branch.common.dto.CommonSuccessDto;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ChatService {
    private final SimpMessagingTemplate messagingTemplate;
    private final ChatRoomRepository chatRoomRepository;
//    private final EmployeeRepository employeeRepository;

    //메시지 발행
    public void publish(ChatSendReqDto dto) {

        messagingTemplate.convertAndSend("/topic/" + dto.getRoomId(), dto);
    }


    public List<ChatMyChatRoomResDto> getMyChatRoom() {
//        Employee employee = getEmployee();
//        List<ChatMyChatRoomResDto> dtos = chatRoomRepository.findAllByUser(getEmployee()).stream().map(ChatRoom::getId).collect(Collectors.toList());
        return null;
    }

    public List<ChatMessageResDto> getChatHistory(Long roomId) {
        ChatRoom chatRoom = chatRoomRepository.findById(roomId).orElseThrow(()-> new EntityNotFoundException("채팅방이 존재하지 않습니다."));
        List<ChatMessageResDto> dtos = chatRoom.getChatMessageList().stream().map(cm-> new ChatMessageResDto().fromChatMessage(chatRoom,cm)).collect(Collectors.toList());
        return dtos;
    }

//    private Employee getEmployee() {
//        return userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName()).orElseThrow(() -> new EntityNotFoundException("존재하지 않는 유저입니다."));
//    }





}
