package com.example.bugle_be.domain.follow.presentation.dto.response;

import com.querydsl.core.annotations.QueryProjection;

import java.util.List;

public record FollowResponse(
    List<UserDto> users
) {
    public record UserDto(
        Long userId,
        String accountId,
        String userName,
        String profileImageObjectKey
    ) {
        @QueryProjection
        public UserDto {
        }
    }
}
