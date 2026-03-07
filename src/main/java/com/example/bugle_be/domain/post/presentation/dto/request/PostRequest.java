package com.example.bugle_be.domain.post.presentation.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record PostRequest(
    @NotBlank(message = "내용은 필수 입력값입니다.")
    @Size(max = 300, message = "내용은 300자 이내로 작성해주세요.")
    String content,

    @Size(max = 150, message = "위치는 150자 이내로 작성해주세요.")
    String location,

    @NotBlank
    String objectKey
) {
}
