package com.example.bugle_be.domain.user.presentation.dto.request;

import com.example.bugle_be.global.util.MessageProperty;
import com.example.bugle_be.global.util.RegexProperty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UserInfoRequest(
    @Pattern(
        regexp = RegexProperty.ACCOUNT_ID,
        message = MessageProperty.ACCOUNT_ID_PATTERN
    )
    @Size(min = 4, max = 20, message = MessageProperty.ACCOUNT_ID_SIZE)
    String accountId,

    @Pattern(
        regexp = RegexProperty.USERNAME,
        message = MessageProperty.USERNAME_PATTERN
    )
    @Size(max = 20, message = MessageProperty.USERNAME_SIZE)
    String userName,

    String profileImageObjectKey
) {
}
