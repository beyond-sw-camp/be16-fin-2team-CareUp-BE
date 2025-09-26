package com.careup.branch.domain.notification.domain;

import com.careup.branch.branch.chat.domain.MemberType;
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
public class Notification extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String body;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private NotificationType type;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private NotificationAction action;

    private Boolean isRead;
    private Boolean isDeleted;
    private String memberEmail;
    private MemberType memberType;
    private String memberName;

    public void readNotification(){
        this.isRead = true;
    }
}
