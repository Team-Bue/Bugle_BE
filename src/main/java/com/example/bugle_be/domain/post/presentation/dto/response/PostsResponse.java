package com.example.bugle_be.domain.post.presentation.dto.response;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;

import java.util.List;

public record PostsResponse(
    List<PostPreviewResponse> posts
) {
    @Builder
    public record PostPreviewResponse(
        Long id,
        String accountId,
        String profileImageUrl,
        String country,
        String region,
        String fileUrl,
        String content
    ) {
        @QueryProjection
        public PostPreviewResponse(Long id, String accountId, String profileImageUrl, String country, String region, String fileUrl, String content) {
            this.id = id;
            this.accountId = accountId;
            this.profileImageUrl = profileImageUrl;
            this.country = country;
            this.region = region;
            this.fileUrl = fileUrl;
            this.content = content;
        }
    }
}
