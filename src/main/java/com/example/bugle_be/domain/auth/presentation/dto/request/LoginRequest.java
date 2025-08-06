package com.example.bugle_be.domain.auth.presentation.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record LoginRequest(
    @Email(message = "올바른 형식의 이메일을 입력해주세요.")
    String email,

    @Pattern(
        regexp = "^(?!\\.)([a-z0-9._]{4,20})(?<!\\.)$",
        message = "아이디는 소문자, 숫자, 언더바(_), 점(.)만 사용 가능하며, 시작과 끝은 점(.)일 수 없습니다."
    )
    @Size(min = 4, max = 20, message = "아이디는 최소 4자 이상, 20자 이하로 입력해주세요.")
    String accountId,

    @Pattern(
        regexp = "^(?=.*[@#!%&*])[a-zA-Z0-9@#!%&*]+$",
        message = "비밀번호는 영어 대소문자, 숫자만 허용되며 @, #, !, %, &, * 중 하나 이상을 포함해야 합니다."
    )
    @NotBlank(message = "비밀번호는 필수 입력 항목입니다.")
    @Size(min = 8, max = 30, message = "비밀번호는 최소 8자 이상, 30자 이하로 입력해주세요.")
    String password
) {
}
