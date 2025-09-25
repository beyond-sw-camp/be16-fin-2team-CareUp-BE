package com.careup.branch.branch.notification.dto;

import com.careup.branch.branch.chat.domain.MemberType;
import com.careup.branch.branch.notification.domain.Notification;
import com.careup.branch.branch.notification.domain.NotificationType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NotificationListResDto {
    private Long id;
    private String title;
    private String body;
    private NotificationType type;
    private LocalDateTime createdTime;

    public static NotificationListResDto fromEntity(Notification notification) {
        return NotificationListResDto
                .builder()
                .title(notification.getTitle())
                .body(notification.getBody())
                .type(notification.getType())
                .createdTime(notification.getCreatedTime())
                .build();
    }
}
