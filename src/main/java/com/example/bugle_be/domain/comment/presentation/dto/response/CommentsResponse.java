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
        public CommentDto {
        }
        public record UserDto(
            Long id,
            String accountId,
            String profileImageUrl
        ) {
            @QueryProjection
            public UserDto {
            }
        }
    }
}
