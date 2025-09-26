package com.careup.branch.domain.chat.entity;


import com.careup.branch.common.domain.BaseTimeEntity;
import com.careup.branch.domain.employee.entity.Employee;
import com.careup.branch.domain.owner.entity.Owner;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChatParticipant extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private MemberType memberType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="chat_room_id")
    private ChatRoom chatRoom;
    private boolean exitYn;

    @OneToMany(mappedBy = "chatParticipant", cascade = CascadeType.REMOVE, orphanRemoval = true)
    @Builder.Default
    private List<ChatReadStatus> chatReadStatusList = new ArrayList<>();

    public ChatParticipant fromEmployee(Employee employee){
        return ChatParticipant.builder()
                .name(employee.getName())
                .email(employee.getEmail())
                .memberType(MemberType.EMPLOYEE)
                .build();
    }

    public ChatParticipant fromOwner(Owner owner) {
        return ChatParticipant.builder()
                .name(owner.getName())
                .email(owner.getEmail())
                .memberType(MemberType.OWNER)
                .build();
    }

    public ChatParticipant fromChatRoomAndOwner(ChatRoom chatRoom, Owner owner) {
        return ChatParticipant.builder()
                .name(owner.getName())
                .email(owner.getEmail())
                .chatRoom(chatRoom)
                .build();
    }
}

