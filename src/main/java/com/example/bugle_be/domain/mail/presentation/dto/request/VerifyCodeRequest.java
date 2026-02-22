package com.example.bugle_be.domain.mail.presentation.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record VerifyCodeRequest(
    @NotBlank(message = "이메일을 입력해주세요")
    @Email(message = "올바른 이메일 형식을 입력해주세요.")
    String email,

    @NotBlank(message = "인증 받은 코드를 입력해주세요.")
    String code
) {
}
