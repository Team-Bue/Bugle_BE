package com.example.bugle_be.infra.elasticsearch.event;

import com.example.bugle_be.domain.post.domain.Post;

public record PostIndexEvent(
    Long postId,
    String content,
    String location,
    String objectKey,
    IndexAction action
) {
    public static PostIndexEvent create(Post post) {
        return new PostIndexEvent(post.getId(), post.getContent(), post.getLocation(), post.getObjectKey(), IndexAction.CREATE);
    }

    public static PostIndexEvent update(Post post) {
        return new PostIndexEvent(post.getId(), post.getContent(), post.getLocation(), post.getObjectKey(), IndexAction.UPDATE);
    }

    public static PostIndexEvent delete(Long postId) {
        return new PostIndexEvent(postId, null, null, null, IndexAction.DELETE);
    }
}
