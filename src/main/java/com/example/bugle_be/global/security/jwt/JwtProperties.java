package com.example.bugle_be.global.security.jwt;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "spring.jwt")
public record JwtProperties(
    String secretKey,
    String header,
    String prefix,
    Long accessExp,
    Long refreshExp
) {
}
