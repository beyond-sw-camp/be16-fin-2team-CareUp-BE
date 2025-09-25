package com.careup.branch.domain.chat.repository;


import com.careup.branch.domain.chat.entity.ChatReadStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ChatReadStatusRepository extends JpaRepository<ChatReadStatus,Long> {


}
