package com.example.bugle_be.domain.search.presentation.dto.request;

import com.example.bugle_be.domain.search.presentation.dto.SearchType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PostSearchRequest(
    @NotNull(message = "검색 타입은 필수입니다.")
    SearchType type,

    @NotBlank(message = "검색 키워드는 필수입니다.")
    String keyword
) {
}
