package com.careup.branch.chat.repository;


import com.careup.branch.chat.domain.ChatReadStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ChatReadStatusRepository extends JpaRepository<ChatReadStatus,Long> {


}
