package com.example.bugle_be.domain.comment.presentation.dto.response;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;

import java.util.List;

public record CommentsResponse(
    List<CommentResponse> comments
) {
    @Builder
    public record CommentResponse(
        Long id,
        String content,
        UserResponse user
    ) {
        @QueryProjection
        public CommentResponse(Long id, String content, UserResponse user) {
            this.id = id;
            this.content = content;
            this.user = user;

        }
        @Builder
        public record UserResponse(
            Long id,
            String accountId,
            String profileImageUrl
        ) {
            @QueryProjection
            public UserResponse(Long id, String accountId, String profileImageUrl) {
                this.id = id;
                this.accountId = accountId;
                this.profileImageUrl = profileImageUrl;
            }
        }
    }
}
