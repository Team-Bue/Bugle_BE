package com.example.bugle_be.domain.post.presentation.dto.response;

import lombok.Builder;

import java.util.List;

public record QueryPostListResponse(
    List<PostPreviewResponse> posts
) {
    @Builder
    public record PostPreviewResponse(
        Long id,
        String accountId,
        String profileImageUrl,
        String country,
        String region,
        String fileUrl
    ) {}
}
