package com.careup.branch.domain.chat.entity;

import com.careup.branch.common.domain.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChatParticipant extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    //todo:추후 멤버 추가할 것
//    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="chat_room_id")
    private ChatRoom chatRoom;


    private boolean exitYn;
    private boolean isOwner;
}

