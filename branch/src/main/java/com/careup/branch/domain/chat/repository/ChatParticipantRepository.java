package com.careup.branch.domain.chat.repository;


import com.careup.branch.domain.chat.entity.ChatParticipant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ChatParticipantRepository extends JpaRepository<ChatParticipant,Long> {

}
