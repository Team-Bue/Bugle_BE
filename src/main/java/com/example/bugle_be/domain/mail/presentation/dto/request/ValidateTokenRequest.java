package com.example.bugle_be.domain.mail.presentation.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record ValidateTokenRequest(
    @Email(message = "올바른 형식의 이메일을 입력해주세요.")
    @NotBlank(message = "이메일은 필수 입력 항목입니다.")
    String email,

    @NotBlank(message = "인증 토큰은 필수 입력 항목입니다.")
    String token
) {
}
