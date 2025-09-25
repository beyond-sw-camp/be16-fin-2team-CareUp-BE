package com.careup.branch.domain.chat.repository;


import com.careup.branch.domain.chat.entity.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ChatRoomRepository extends JpaRepository<ChatRoom,Long> {

//    List<ChatRoom> findAllByUser();
}
