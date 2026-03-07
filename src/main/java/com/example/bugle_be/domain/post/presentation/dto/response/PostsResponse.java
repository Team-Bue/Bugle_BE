package com.example.bugle_be.domain.post.presentation.dto.response;

import com.querydsl.core.annotations.QueryProjection;

import java.util.List;

public record PostsResponse(
    List<PostPreviewResponse> posts
) {
    public record PostPreviewResponse(
        Long id,
        String accountId,
        String profileImageObjectKey,
        String location,
        String objectKey,
        String content
    ) {
        @QueryProjection
        public PostPreviewResponse {
        }
    }
}
