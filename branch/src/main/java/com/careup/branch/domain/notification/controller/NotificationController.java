package com.careup.branch.domain.notification.controller;

import com.careup.branch.common.dto.CommonSuccessDto;
import com.careup.branch.domain.notification.dto.NotificationListResDto;
import com.careup.branch.domain.notification.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/noti")
public class NotificationController {
    private NotificationService notificationService;

    @GetMapping("/list")
    public ResponseEntity<?> getNotificationList() {
        List<NotificationListResDto> notificationListResDtoList = notificationService.getNotificationList();
        return new ResponseEntity<>(new CommonSuccessDto(notificationListResDtoList, HttpStatus.OK.value(), "알림목록 조회 성공"), HttpStatus.OK);
    }

    // 알림 읽음상태 변경
    @PatchMapping("/updateState/{notiId}")
    public ResponseEntity<?> readNotification(@PathVariable Long notificationId) {
        notificationService.readNotification(notificationId);
        return new ResponseEntity<>(new CommonSuccessDto(notificationId, HttpStatus.OK.value(), "알림상태 읽음으로 수정 성공"), HttpStatus.OK);
    }
}
