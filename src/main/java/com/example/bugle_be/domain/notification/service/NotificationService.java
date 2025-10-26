package com.example.bugle_be.domain.notification.service;

import com.example.bugle_be.domain.notification.domain.Notification;
import com.example.bugle_be.domain.notification.domain.repository.NotificationRepository;
import com.example.bugle_be.domain.user.domain.User;
import com.example.bugle_be.infra.fcm.service.FCMService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final FCMService fcmService;
    private final NotificationRepository notificationRepository;

    public void sendFollowNotification(User user, String title) {
        Notification notification = notificationRepository.save(Notification.builder()
            .user(user)
            .title(title)
            .deviceToken(user.getDeviceToken())
            .build());

        fcmService.sendMessage(user.getDeviceToken(), notification);
    }

    public void sendLikeNotification(User user, String title) {
        Notification notification = notificationRepository.save(Notification.builder()
            .user(user)
            .title(title)
            .deviceToken(user.getDeviceToken())
            .build());

        fcmService.sendMessage(user.getDeviceToken(), notification);
    }
}
