package com.careup.branch.domain.chat.repository;


import com.careup.branch.domain.chat.entity.ChatParticipant;
import com.careup.branch.domain.chat.entity.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface ChatParticipantRepository extends JpaRepository<ChatParticipant,Long> {
    Optional<ChatParticipant> findByChatRoomAndEmail(ChatRoom chatRoom, String email);
}
