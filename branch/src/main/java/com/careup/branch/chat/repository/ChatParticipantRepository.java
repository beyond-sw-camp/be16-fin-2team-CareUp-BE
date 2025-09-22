package com.careup.branch.chat.repository;


import com.careup.branch.chat.domain.ChatParticipant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ChatParticipantRepository extends JpaRepository<ChatParticipant,Long> {

}
