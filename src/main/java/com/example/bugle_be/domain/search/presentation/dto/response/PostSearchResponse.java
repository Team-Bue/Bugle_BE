package com.example.bugle_be.domain.search.presentation.dto.response;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

import java.util.List;

public record PostSearchResponse(
    List<PostResponse> posts
) {
    @Getter
    public static class PostResponse {
        private final Long id;
        private final String fileUrl;

        @QueryProjection
        public PostResponse(Long id, String fileUrl) {
            this.id = id;
            this.fileUrl = fileUrl;
        }
    }
}
