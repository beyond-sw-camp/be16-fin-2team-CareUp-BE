package com.careup.branch.domain.chat.dto.res;

import com.careup.branch.domain.chat.entity.ChatMessage;
import com.careup.branch.domain.chat.entity.ChatRoom;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChatMessageResDto {
    private Long roomId;
    private String content;
    private LocalDateTime timestamp;
    private String senderEmail;
    private String senderName;
    public ChatMessageResDto fromChatMessage(ChatRoom chatRoom,ChatMessage chatMessage){
        return ChatMessageResDto.builder()
                .roomId(chatRoom.getId())
                .content(chatMessage.getContent())
                .timestamp(chatMessage.getCreatedTime())
                //todo : 추후 수정
//                .senderEmail()
//                .senderName()
                .build();
    }
}
