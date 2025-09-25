package com.careup.branch.branch.chat.repository;


import com.careup.branch.branch.chat.domain.ChatParticipant;
import com.careup.branch.branch.chat.domain.ChatReadStatus;
import com.careup.branch.branch.chat.domain.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ChatReadStatusRepository extends JpaRepository<ChatReadStatus,Long> {


    List<ChatReadStatus> findAllByChatRoomAndChatParticipantAndIsReadFalse(ChatRoom chatRoom, ChatParticipant chatParticipant);
}
