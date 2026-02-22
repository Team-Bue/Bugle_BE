package com.example.bugle_be.domain.search.presentation.dto.request;

import jakarta.validation.constraints.NotBlank;

public record UserSearchRequest(
    @NotBlank(message = "검색 키워드는 필수입니다.")
    String keyword
) {
}
