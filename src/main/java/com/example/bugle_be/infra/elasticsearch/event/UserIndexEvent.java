package com.example.bugle_be.infra.elasticsearch.event;

public record UserIndexEvent(
    Long userId,
    String accountId,
    String userName,
    String profileImageObjectKey,
    IndexAction action
) {
}
