package com.example.bugle_be.domain.auth.presentation.dto.response;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record TokenResponse(
    String accessToken,
    String refreshToken,
    LocalDateTime accessExp,
    LocalDateTime refreshExp
) {
}
