package com.example.bugle_be.infra.fcm.config;

import com.example.bugle_be.infra.fcm.exception.FCMInitializationFailed;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;

@Configuration
public class FirebaseConfig {

    @Value("${fcm.json}")
    private String fcmJson;

    @PostConstruct
    public void init() {
        try {
            if (!FirebaseApp.getApps().isEmpty()) {
                return;
            }

            try (ByteArrayInputStream account =
                     new ByteArrayInputStream(fcmJson.getBytes(StandardCharsets.UTF_8))) {

                FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(account))
                    .build();

                FirebaseApp.initializeApp(options);
            }
        } catch (Exception e) {
            throw FCMInitializationFailed.EXCEPTION;
        }
    }
}
