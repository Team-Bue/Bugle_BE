package com.example.bugle_be.domain.notification.service;

import com.example.bugle_be.domain.notification.domain.Notification;
import com.example.bugle_be.domain.notification.domain.repository.NotificationRepository;
import com.example.bugle_be.domain.user.domain.User;
import com.example.bugle_be.infra.fcm.service.FCMService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionSynchronization;
import org.springframework.transaction.support.TransactionSynchronizationManager;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final FCMService fcmService;
    private final NotificationRepository notificationRepository;

    public void execute(User user, String title) {
        Notification notification = notificationRepository.save(Notification.builder()
            .user(user)
            .title(title)
            .build());

        TransactionSynchronizationManager.registerSynchronization(
            new TransactionSynchronization() {
                @Override
                public void afterCommit() {
                    fcmService.sendMessage(user.getDeviceToken(), notification);
                }
            }
        );
    }
}
