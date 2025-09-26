package com.careup.branch.domain.notification.service;

import com.careup.branch.branch.chat.domain.MemberType;
import com.careup.branch.branch.employee.domain.Employee;
import com.careup.branch.branch.employee.repository.EmployeeRepository;
import com.careup.branch.branch.owner.domain.Owner;
import com.careup.branch.branch.owner.repository.OwnerRepository;
import com.careup.branch.domain.notification.domain.Notification;
import com.careup.branch.domain.notification.domain.NotificationAction;
import com.careup.branch.domain.notification.domain.NotificationType;
import com.careup.branch.domain.notification.dto.NotificationListResDto;
import com.careup.branch.domain.notification.repository.NotificationRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class NotificationService {
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final NotificationRepository notificationRepository;
    private final EmployeeRepository employeeRepository;
    private final OwnerRepository ownerRepository;

    public void publish(String topic, String message) {
        kafkaTemplate.send(topic, message);
    }
    // [ 1. 근태 알림]

    // ATTENDANCE: CHECK_IN
    public Long createNotAttendanceCheckIn(String email, MemberType memberType) {
        String name = getNotificationName(email,memberType);
        String title = "근태 확인 완료 ✅";
        String body = name + "님이 출근을 체크했습니다.";

        Notification notification = Notification.builder()
                .title(title)
                .body(body)
                .type(NotificationType.ATTENDANCE)
                .action(NotificationAction.CHECK_IN)
                .memberEmail(email)
                .memberType(memberType)
                .build();

        notificationRepository.save(notification);
        return notification.getId();
    }

    // ATTENDANCE: CHECK_OUT
    public Long createNotiAttendanceCheckOut(String email, MemberType memberType) {
        String name = getNotificationName(email,memberType);
        String title = "퇴근 처리 완료 🏠";
        String body = name + "님이 퇴근을 체크했습니다.";

        Notification notification = Notification.builder()
                .title(title)
                .body(body)
                .type(NotificationType.ATTENDANCE)
                .action(NotificationAction.CHECK_OUT)
                .memberEmail(email)
                .memberType(memberType)
                .build();

        notificationRepository.save(notification);
        return notification.getId();
    }

    // ORDER: ORDER_PLACED
    public Long createNotiOrderPlaced(String memberName, MemberType memberType, Long orderId) {
        String title = "주문 접수 완료 🛒";
        String body = "주문번호 [" + orderId + "]가 접수되었습니다.";

        Notification notification = Notification.builder()
                .title(title)
                .body(body)
                .type(NotificationType.ORDER)
                .action(NotificationAction.ORDER_PLACED)
                .memberName(memberName)
                .memberType(memberType)
                .build();

        notificationRepository.save(notification);
        return notification.getId();
    }

    // ORDER: ORDER_CANCELLED
    public Long createNotiOrderCancelled(String memberName,  MemberType memberType, Long orderId) {
        String title = "주문 취소 ❌";
        String body = "주문번호 [" + orderId + "]가 취소되었습니다.";

        Notification notification = Notification.builder()
                .title(title)
                .body(body)
                .type(NotificationType.ORDER)
                .action(NotificationAction.ORDER_CANCELLED)
                .memberName(memberName)
                .memberType(memberType)
                .build();

        notificationRepository.save(notification);
        return notification.getId();
    }

    // STOCK: STOCK_LOW
    public Long createNotiStockLow(String email, MemberType memberType, String itemName) {
        String title = "재고 부족 ⚠️";
        String body = "[" + itemName + "]의 재고가 부족합니다.";

        Notification notification = Notification.builder()
                .title(title)
                .body(body)
                .type(NotificationType.STOCK)
                .action(NotificationAction.STOCK_LOW)
                .memberEmail(email)
                .memberType(memberType)
                .build();

        notificationRepository.save(notification);
        return notification.getId();
    }

    // ANNOUNCEMENT: ANNOUNCEMENT_CREATED
    public Long createNotiAnnouncementCreated(String email, MemberType memberType, String titleMsg) {
        String title = "새로운 공지사항 📢";
        String body = "공지 [" + titleMsg + "]가 등록되었습니다.";

        Notification notification = Notification.builder()
                .title(title)
                .body(body)
                .type(NotificationType.ANNOUNCEMENT)
                .action(NotificationAction.ANNOUNCEMENT_CREATED)
                .memberEmail(email)
                .memberType(memberType)
                .build();

        notificationRepository.save(notification);
        return notification.getId();
    }

//    // CHAT: MESSAGE_RECEIVED
//    public Long createNotiMessageReceived(Long unReadMessageCount, ) {
//        String title = "새 메시지 도착 ✉️";
//        String body = "[" + fromUser + "]님으로부터 새 메시지가 도착했습니다.";
//
//        Notification notification = Notification.builder()
//                .title(title)
//                .body(body)
//                .type(NotificationType.CHAT)
//                .action(NotificationAction.MESSAGE_RECEIVED)
//                .expiresAtTime(LocalDateTime.now().plusDays(7))
//                .build();
//
//        notificationRepository.save(notification);
//        return notification.getId();
//    }


    // [알림 목록 불러오기]
    public List<NotificationListResDto> getNotificationList() {
        String email = null;
//        String email = SecurityContextHolder.getContext().getAuthentication().getName();
//        List<NotificationListResDto> notificationListResDtoList
//                = notificationRepository.findAllByUserAndState(user, NotificationState.UNREAD).stream().map(a -> NotificationListResDto.fromEntity(a)).toList();

        List<NotificationListResDto> notificationListResDtoList
                = notificationRepository.findByMemberEmailAndIsReadFalse(email).stream().map(NotificationListResDto::fromEntity).toList();

        log.info("[CAREUP][INFO] - NotificationService/getNotificationList - 알림목록 조회 성공");

        return notificationListResDtoList;
    }

    // [알림 읽음 처리]
    public void readNotification(Long notificationId) {

        notificationRepository.findById(notificationId).orElseThrow(()->new EntityNotFoundException("존재하지 않는 알림 입니다.")).readNotification();

        log.info("[CAREUP][INFO] - NotificationService/getNotificationList - 알림 읽음 처리 성공");
    }

    public String getNotificationName(String email, MemberType memberType) {
        switch (memberType) {
            case EMPLOYEE:
                return employeeRepository.findByEmail(email)
                        .map(Employee::getName)
                        .orElse("Unknown");
            case OWNER:
                return ownerRepository.findByEmail(email)
                        .map(Owner::getName)
                        .orElse("Unknown");
            default:
                return "Unknown";
        }
    }

}
