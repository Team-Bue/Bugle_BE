package com.example.bugle_be.infra.fcm.service;

import com.example.bugle_be.domain.notification.domain.Notification;
import com.example.bugle_be.infra.fcm.exception.DeviceTokenNotFound;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class FCMService {

    public void sendMessage(String token, Notification notification) {
        if (token == null || token.isEmpty()) {
            throw DeviceTokenNotFound.EXCEPTION;
        }

        try {
            Message message = Message.builder()
                .setToken(token)
                .setNotification(com.google.firebase.messaging.Notification.builder()
                    .setTitle(notification.getTitle())
                    .build())
                .build();

            FirebaseMessaging.getInstance().send(message);
        } catch (FirebaseMessagingException e) {
            log.error("Failed to send message", e);
        }
    }
}
