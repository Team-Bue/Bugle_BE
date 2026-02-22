package com.example.bugle_be.domain.mail.presentation.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record SendCodeRequest(
    @NotBlank(message = "이메일을 입력해주세요.")
    @Email(message = "올바른 Email 형식을 입력해주세요.")
    String email
) {
}
