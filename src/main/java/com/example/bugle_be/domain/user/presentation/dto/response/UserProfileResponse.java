package com.example.bugle_be.domain.user.presentation.dto.response;

import com.example.bugle_be.domain.user.domain.User;

public record UserProfileResponse(
    Long userId,
    String accountId,
    String userName,
    String profileImageObjectKey
) {
    public static UserProfileResponse from(User user) {
        return new UserProfileResponse(
            user.getId(),
            user.getAccountId(),
            user.getUserName(),
            user.getProfileImageObjectKey()
        );
    }
}
