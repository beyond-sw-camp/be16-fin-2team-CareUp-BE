package com.careup.branch.branch.chat.domain;

import com.careup.branch.branch.employee.entity.Employee;
import com.careup.branch.branch.owners.entity.Owners;
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

    private String name;
    private String email;
    private MemberType memberType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="chat_room_id")
    private ChatRoom chatRoom;
    private boolean exitYn;

    public ChatParticipant fromEmployee(Employee employee){
        return ChatParticipant.builder()
                .name(employee.getName())
                .email(employee.getEmail())
                .memberType(MemberType.EMPLOYEE)
                .build();
    }

    public ChatParticipant fromOwner(Owners owner) {
        return ChatParticipant.builder()
                .name(owner.getName())
                .email(owner.getEmail())
                .memberType(MemberType.OWNER)
                .build();
    }
}

