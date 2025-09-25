package com.careup.branch.branch.chat.repository;


import com.careup.branch.branch.chat.domain.ChatParticipant;
import com.careup.branch.branch.chat.domain.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface ChatParticipantRepository extends JpaRepository<ChatParticipant,Long> {

    Optional<ChatParticipant> findByChatRoomAndEmail(ChatRoom chatRoom, String email);
}
