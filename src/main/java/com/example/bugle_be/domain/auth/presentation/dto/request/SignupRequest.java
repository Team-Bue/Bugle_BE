package com.example.bugle_be.domain.auth.presentation.dto.request;

import com.example.bugle_be.global.util.MessageProperty;
import com.example.bugle_be.global.util.RegexProperty;
import jakarta.validation.constraints.*;

public record SignupRequest(
    @Email(message = MessageProperty.EMAIL_INVALID)
    @NotBlank(message = MessageProperty.EMAIL_NOT_BLANK)
    String email,

    @NotBlank(message = MessageProperty.TOKEN_NOT_BLANK)
    String token,

    @Pattern(
        regexp = RegexProperty.PASSWORD,
        message = MessageProperty.PASSWORD_PATTERN
    )
    @NotBlank(message = MessageProperty.PASSWORD_NOT_BLANK)
    @Size(min = 8, max = 30, message = MessageProperty.PASSWORD_SIZE)
    String password,

    @Pattern(
        regexp = RegexProperty.ACCOUNT_ID,
        message = MessageProperty.ACCOUNT_ID_PATTERN
    )
    @NotBlank(message = MessageProperty.ACCOUNT_ID_NOT_BLANK)
    @Size(min = 4, max = 20, message = MessageProperty.ACCOUNT_ID_SIZE)
    String accountId,

    @Pattern(
        regexp = RegexProperty.USERNAME,
        message = MessageProperty.USERNAME_PATTERN
    )
    @Size(max = 20, message = MessageProperty.USERNAME_SIZE)
    String userName
) {
}
