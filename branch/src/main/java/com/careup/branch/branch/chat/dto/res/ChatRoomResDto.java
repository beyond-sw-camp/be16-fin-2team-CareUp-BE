package com.careup.branch.branch.chat.dto.res;

import com.careup.branch.branch.chat.domain.ChatMessage;
import com.careup.branch.branch.chat.domain.ChatRoom;
import com.careup.branch.branch.chat.domain.ChatRoomType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChatRoomResDto {
    private String address;
    private String lastMessage;
    private LocalDateTime lastMessageTime;
    private int participantCount;
    private ChatRoomType chatRoomType;

    public static ChatRoomResDto fromChatRoom(ChatRoom chatRoom){
        List<ChatMessage> messages = chatRoom.getChatMessageList();
        String lastMessageContent = null;
        LocalDateTime lastMessageContentTime =null;
        ChatRoomType roomType = chatRoom.getChatRoomType();

        if (messages.isEmpty()) {
            lastMessageContent = "아직 작성된 메시지가 없습니다";
            lastMessageContentTime = null; // 또는 기본값
        } else {
            ChatMessage lastMessage = messages.get(messages.size() - 1);
            lastMessageContent = lastMessage.getContent();
            lastMessageContentTime = lastMessage.getCreatedTime();
        }

        return ChatRoomResDto.builder()
                .address(chatRoom.getAddress())
                .lastMessage(lastMessageContent)
                .lastMessageTime(lastMessageContentTime)
                .participantCount(chatRoom.getParticipantList().size())
                .chatRoomType(roomType)
                .build();
    }
}
