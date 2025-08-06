package com.example.bugle_be.domain.search.presentation.dto.response;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

import java.util.List;

public record UserSearchResponse(
    List<UserResponse> users
) {
    @Getter
    public static class UserResponse {
        private final Long id;
        private final String accountId;
        private final String userName;
        private final String profileImageUrl;

        @QueryProjection
        public UserResponse(Long id, String accountId, String userName, String profileImageUrl) {
            this.id = id;
            this.accountId = accountId;
            this.userName = userName;
            this.profileImageUrl = profileImageUrl;
        }
    }
}
