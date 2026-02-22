package com.example.bugle_be.domain.notification.domain.repository;

import com.example.bugle_be.domain.notification.domain.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
}
