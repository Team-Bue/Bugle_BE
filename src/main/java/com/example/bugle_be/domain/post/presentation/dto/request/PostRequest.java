package com.example.bugle_be.domain.post.presentation.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record PostRequest(

    @NotBlank
    @Size(max = 50, message = "제목은 50자 이내로 작성해주세요.")
    String title,

    @NotBlank
    @Size(max = 300, message = "내용은 300자 이내로 작성해주세요.")
    String content,

    @Size(max = 20, message = "나라는 20자 이내로 작성해주세요.")
    String country,

    @Size(max = 20, message = "지역은 20자 이내로 작성해주세요.")
    String region,

    @NotBlank
    String objectKey
) {
}
