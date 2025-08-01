package com.example.bugle_be.global.image;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "profile")
public record DefaultImageProperties(
    String defaultImageUrl
) {
}
