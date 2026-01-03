package com.example.bugle_be.domain.comment.presentation.dto.response;

import com.querydsl.core.annotations.QueryProjection;

import java.util.List;

public record CommentsResponse(
    List<CommentDto> comments
) {
    public record CommentDto(
        Long id,
        String content,
        UserDto user
    ) {
        @QueryProjection
        public CommentDto(Long id, String content, UserDto user) {
            this.id = id;
            this.content = content;
            this.user = user;
        }
        public record UserDto(
            Long id,
            String accountId,
            String profileImageUrl
        ) {
            @QueryProjection
            public UserDto(Long id, String accountId, String profileImageUrl) {
                this.id = id;
                this.accountId = accountId;
                this.profileImageUrl = profileImageUrl;
            }
        }
    }
}
