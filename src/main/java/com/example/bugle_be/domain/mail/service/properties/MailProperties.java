package com.example.bugle_be.domain.mail.service.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "spring.mail.security")
public record MailProperties(
    String secret
) {
}
