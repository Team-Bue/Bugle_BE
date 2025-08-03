package com.example.bugle_be.domain.user.profile;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "user.profile")
public record DefaultImageProperties(
    String defaultImageUrl
) {
}
