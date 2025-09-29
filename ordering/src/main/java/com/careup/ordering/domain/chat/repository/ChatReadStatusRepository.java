package com.careup.ordering.domain.chat.repository;


import com.careup.ordering.domain.chat.entity.ChatParticipant;
import com.careup.ordering.domain.chat.entity.ChatReadStatus;
import com.careup.ordering.domain.chat.entity.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ChatReadStatusRepository extends JpaRepository<ChatReadStatus,Long> {
    List<ChatReadStatus> findAllByChatRoomAndChatParticipantAndIsReadFalse(ChatRoom chatRoom, ChatParticipant chatParticipant);

}
