package com.example.bugle_be.domain.follow.presentation.dto.response;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;

import java.util.List;

public record FollowResponse(
    List<UserDto> followers
) {
    @Builder
    public record UserDto(
        Long followerId,
        String accountId,
        String userName,
        String profileImageUrl
    ) {
        @QueryProjection
        public UserDto(Long followerId, String accountId, String userName, String profileImageUrl) {
            this.followerId = followerId;
            this.accountId = accountId;
            this.userName = userName;
            this.profileImageUrl = profileImageUrl;
        }
    }
}
