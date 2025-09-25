package com.careup.branch.branch.notification.repository;

import com.careup.branch.branch.notification.domain.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;


@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

    List<Notification> findByMemberEmailAndIsReadFalse(String email);
}
