package com.example.bugle_be.domain.notification.message;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum NotificationMessage {

    FOLLOW("님이 회원님을 팔로우 했습니다!"),
    LIKE("님이 회원님의 게시글을 좋아합니다!");

    private final String message;
}
