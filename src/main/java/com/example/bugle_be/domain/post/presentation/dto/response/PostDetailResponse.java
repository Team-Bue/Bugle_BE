package com.example.bugle_be.domain.post.presentation.dto.response;

import com.querydsl.core.annotations.QueryProjection;

import java.time.LocalDateTime;

public record PostDetailResponse(
    Long id,
    String content,
    String location,
    String objectKey,
    LocalDateTime createdAt,
    UserDto user
) {
    @QueryProjection
    public PostDetailResponse {
    }

    public record UserDto(
        Long id,
        String accountId,
        String profileImageObjectKey
    ) {
        @QueryProjection
        public UserDto {
        }
    }
}
