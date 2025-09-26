package com.careup.branch.domain.chat.service;

import com.careup.branch.domain.branch.entity.Branch;
import com.careup.branch.domain.branch.repository.BranchRepository;
import com.careup.branch.domain.chat.dto.res.ChatRoomResDto;
import com.careup.branch.domain.chat.entity.ChatParticipant;
import com.careup.branch.domain.chat.entity.ChatReadStatus;
import com.careup.branch.domain.chat.entity.ChatRoom;
import com.careup.branch.domain.chat.dto.ChatSendReqDto;
import com.careup.branch.domain.chat.dto.res.ChatMessageResDto;
import com.careup.branch.domain.chat.repository.ChatParticipantRepository;
import com.careup.branch.domain.chat.repository.ChatReadStatusRepository;
import com.careup.branch.domain.chat.repository.ChatRoomRepository;
import com.careup.branch.domain.employee.entity.Employee;
import com.careup.branch.domain.employee.repository.EmployeeRepository;
import com.careup.branch.domain.owner.entity.Owner;
import com.careup.branch.domain.owner.repository.OwnerRepository;
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
    private final OwnerRepository ownerRepository;
    private final BranchRepository branchRepository;
    private final ChatParticipantRepository chatParticipantRepository;
    private final ChatReadStatusRepository chatReadStatusRepository;

    // 본사 - 모든 채팅방 조회
    public List<ChatRoomResDto> getAllGroupChat() {
        List<String> addressList = getBranchAddressList();
        return addressList.stream()
                .map(address -> chatRoomRepository.findByAddress(address).orElse(null))
                .filter(Objects::nonNull)
                .map(ChatRoomResDto::fromChatRoom)
                .toList();
    }

    //본사 - 단체 채팅방 생성
    public List<ChatRoomResDto> createGroupChat() {
        List<String> addressList = getBranchAddressList();
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
    public void joinGroupChatRoom(Long chatRoomId) {
        //이미 채팅방에 가입되어 있는지를 검증
        ChatRoom chatRoom = getChatRoom(chatRoomId);
        Owner owner = getOwner();
        ChatParticipant participant = getChatParticipant(chatRoom, owner.getEmail());

        //가입되어 있지 않다면 새로운 참여자 추가
        if (participant == null) {
            participant = new ChatParticipant().fromChatRoomAndOwner(chatRoom,owner);
            chatParticipantRepository.save(participant);
        }

    }

    //메시지 발행
    public void publish(ChatSendReqDto dto) {
        messagingTemplate.convertAndSend("/topic/" + dto.getRoomId(), dto);
    }

    //메시지 로그 조회
    public List<ChatMessageResDto> getChatHistory(Long roomId) {
        ChatRoom chatRoom = getChatRoom(roomId);
        List<ChatMessageResDto> dtos = chatRoom.getChatMessageList().stream().map(cm-> new ChatMessageResDto().fromChatMessage(chatRoom,cm)).collect(Collectors.toList());
        return dtos;
    }

    //메시지 읽음 처리
    public void getReadMessage(Long roomId){
        ChatRoom chatRoom = getChatRoom(roomId);
        Owner owner = getOwner();
        ChatParticipant chatParticipant = getChatParticipant(chatRoom, owner.getEmail());
        List<ChatReadStatus> chatReadStatuses = chatReadStatusRepository.findAllByChatRoomAndChatParticipantAndIsReadFalse(chatRoom,chatParticipant);
        chatReadStatuses.stream().forEach(ChatReadStatus::readMessage);
    }


    //채팅방 가져오기
    private ChatRoom getChatRoom(Long roomId){
        return chatRoomRepository.findById(roomId).orElseThrow(()-> new EntityNotFoundException("채팅방이 존재하지 않습니다."));
    }

    //채팅 참여자 가져오기
    private ChatParticipant getChatParticipant(ChatRoom chatRoom, String email){
        return chatParticipantRepository
                .findByChatRoomAndEmail(chatRoom, email)
                .orElse(null);
    }

    //직원 가져오기
    private Employee getEmployee() {
        //todo : securitycontextholder추가해줄것
//        return employeeRepository.findByEmail();
        return null;
    }

    private List<String> getBranchAddressList(){
        return branchRepository.findAll().stream().map(Branch::getAddress).collect(Collectors.toList());
    }

    private Owner getOwner(){
        //todo : securitycontextholder 연결해주기
        return null;
//        return ownerRepository.findByEmail()
    }


}
