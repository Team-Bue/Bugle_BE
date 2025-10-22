package com.example.bugle_be.domain.auth.presentation.dto.request;

import com.example.bugle_be.global.util.MessageProperty;
import jakarta.validation.constraints.NotBlank;

public record TokenRequest(
    @NotBlank(message = MessageProperty.DEVICE_TOKEN_NOT_BLANK)
    String deviceToken
) {
}
