package com.example.bugle_be.domain.follow.presentation.dto.response;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;

import java.util.List;

public record FollowResponse(
    List<UserDto> users
) {
    @Builder
    public record UserDto(
        Long userId,
        String accountId,
        String userName,
        String profileImageUrl
    ) {
        @QueryProjection
        public UserDto(Long userId, String accountId, String userName, String profileImageUrl) {
            this.userId = userId;
            this.accountId = accountId;
            this.userName = userName;
            this.profileImageUrl = profileImageUrl;
        }
    }
}
