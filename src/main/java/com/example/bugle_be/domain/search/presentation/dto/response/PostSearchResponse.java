package com.example.bugle_be.domain.search.presentation.dto.response;

import com.querydsl.core.annotations.QueryProjection;

import java.util.List;

public record PostSearchResponse(
    List<PostResponse> posts
) {
    public record PostResponse(
        Long id,
        String objectKey
    ) {
        @QueryProjection
        public PostResponse {
        }
    }
}
