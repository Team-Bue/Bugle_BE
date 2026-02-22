package com.example.bugle_be.domain.auth.presentation.dto.response;

import java.time.LocalDateTime;

public record TokenResponse(
    String accessToken,
    String refreshToken,
    LocalDateTime accessExp,
    LocalDateTime refreshExp
) {
    public static TokenResponse of(
        String accessToken, String refreshToken,
        LocalDateTime accessExp, LocalDateTime refreshExp
    ) {
        return new TokenResponse(accessToken, refreshToken, accessExp, refreshExp);
    }
}
