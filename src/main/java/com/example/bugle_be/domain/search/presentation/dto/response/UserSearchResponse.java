package com.example.bugle_be.domain.search.presentation.dto.response;

import com.querydsl.core.annotations.QueryProjection;

import java.util.List;

public record UserSearchResponse(
    List<UserResponse> users
) {
    public record UserResponse(
        Long id,
        String accountId,
        String userName,
        String profileImageUrl
    ) {
        @QueryProjection
        public UserResponse {
        }
    }
}
