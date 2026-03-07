package com.example.bugle_be.infra.elasticsearch.event;

public record PostIndexEvent(
    Long postId,
    String content,
    String location,
    String objectKey,
    IndexAction action
) {
}
