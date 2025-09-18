package com.example.bugle_be.domain.search.presentation.dto.response;

import com.querydsl.core.annotations.QueryProjection;

import java.util.List;

public record PostSearchResponse(
    List<PostResponse> posts
) {
    public record PostResponse(
        Long id,
        String fileUrl
    ) {
        @QueryProjection
        public PostResponse(Long id, String fileUrl) {
            this.id = id;
            this.fileUrl = fileUrl;
        }
    }
}
