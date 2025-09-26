package com.careup.branch.domain.notification.dto;

import com.careup.branch.domain.notification.domain.Notification;
import com.careup.branch.domain.notification.domain.NotificationType;
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
