package com.example.bugle_be.domain.auth.presentation.dto.request;

import com.example.bugle_be.global.util.MessageProperty;
import com.example.bugle_be.global.util.RegexProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record LoginRequest(
    @NotBlank(message = MessageProperty.LOGIN_ID_NOT_BLANK)
    String loginId,

    @Pattern(
        regexp = RegexProperty.PASSWORD,
        message = MessageProperty.PASSWORD_PATTERN
    )
    @NotBlank(message = MessageProperty.PASSWORD_NOT_BLANK)
    @Size(min = 8, max = 30, message = MessageProperty.PASSWORD_SIZE)
    String password
) {
}
