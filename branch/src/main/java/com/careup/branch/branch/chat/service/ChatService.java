package com.careup.branch.branch.chat.service;

import com.careup.branch.branch.chat.domain.ChatParticipant;
import com.careup.branch.branch.chat.domain.ChatRoom;
import com.careup.branch.branch.chat.domain.MemberType;
import com.careup.branch.branch.chat.dto.ChatSendReqDto;
import com.careup.branch.branch.chat.dto.req.ChatMemberReqDto;
import com.careup.branch.branch.chat.dto.res.ChatMessageResDto;
import com.careup.branch.branch.chat.dto.res.ChatMyChatRoomResDto;
import com.careup.branch.branch.chat.dto.res.ChatRoomResDto;
import com.careup.branch.branch.chat.repository.ChatRoomRepository;
import com.careup.branch.branch.employee.entity.Employee;
import com.careup.branch.branch.employee.repository.EmployeeRepository;
import com.careup.branch.branch.owners.entity.Owners;
import com.careup.branch.branch.owners.repository.OwnersRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ChatService {
    private final SimpMessagingTemplate messagingTemplate;
    private final ChatRoomRepository chatRoomRepository;
    private final EmployeeRepository employeeRepository;
    private final OwnersRepository ownerRepository;

    // 본사 - 모든 채팅방 조회
    public List<ChatRoomResDto> getAllGroupChat(List<String> addressList) {
        return addressList.stream()
                .map(address -> chatRoomRepository.findByAddress(address).orElse(null))
                .filter(Objects::nonNull)
                .map(ChatRoomResDto::fromChatRoom)
                .toList();
    }

    //본사 - 단체 채팅방 생성
    public List<ChatRoomResDto> createGroupChat(List<String> addressList) {
        return addressList.stream()
                .map(address -> {
                    ChatRoom chatRoom = new ChatRoom();
                    chatRoom.addAddress(address);
                    return chatRoomRepository.save(chatRoom);
                })
                .map(ChatRoomResDto::fromChatRoom)
                .toList();
    }

    // 점주 - 단체 채팅방 조회
    public ChatRoomResDto getGroupChatRoom(String address) {
       ChatRoom chatRoom = chatRoomRepository.findByAddress(address).orElseThrow(()->new EntityNotFoundException("채팅방이 존재하지 않습니다. 본사에 문의해주세요"));
       return ChatRoomResDto.fromChatRoom(chatRoom);
    }

    // 점주 - 단체 채팅방 가입
    public void joinChatRoom(Long chatRoomId) {
        ChatRoom chatRoom = chatRoomRepository.findById(chatRoomId).orElseThrow(()->new EntityNotFoundException("채팅방이 존재하지 않습니다. 본사에 문의해주세요"));
    }

    //메시지 발행
    public void publish(ChatSendReqDto dto) {
        messagingTemplate.convertAndSend("/topic/" + dto.getRoomId(), dto);
    }

    public List<ChatMessageResDto> getChatHistory(Long roomId) {
        ChatRoom chatRoom = chatRoomRepository.findById(roomId).orElseThrow(()-> new EntityNotFoundException("채팅방이 존재하지 않습니다."));
        List<ChatMessageResDto> dtos = chatRoom.getChatMessageList().stream().map(cm-> new ChatMessageResDto().fromChatMessage(chatRoom,cm)).collect(Collectors.toList());
        return dtos;
    }

    private Employee getEmployee() {
        //todo : securitycontextholder추가해줄것
//        return employeeRepository.findByEmail();
        return null;
    }

}
