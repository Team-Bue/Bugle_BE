package com.example.bugle_be.infra.elasticsearch.event;

import com.example.bugle_be.domain.user.domain.User;

public record UserIndexEvent(
    Long userId,
    String accountId,
    String userName,
    String profileImageObjectKey,
    IndexAction action
) {
    public static UserIndexEvent create(User user) {
        return new UserIndexEvent(user.getId(), user.getAccountId(), user.getUserName(), user.getProfileImageObjectKey(), IndexAction.CREATE);
    }

    public static UserIndexEvent delete(Long userId) {
        return new UserIndexEvent(userId, null, null, null, IndexAction.DELETE);
    }
}
