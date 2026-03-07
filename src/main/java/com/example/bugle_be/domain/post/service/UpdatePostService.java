package com.example.bugle_be.domain.post.service;

import com.example.bugle_be.domain.post.domain.Post;
import com.example.bugle_be.domain.post.exception.CannotUpdatePost;
import com.example.bugle_be.domain.post.facade.PostFacade;
import com.example.bugle_be.domain.post.presentation.dto.request.PostRequest;
import com.example.bugle_be.domain.user.domain.User;
import com.example.bugle_be.domain.user.facade.UserFacade;
import com.example.bugle_be.infra.elasticsearch.event.PostIndexEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UpdatePostService {

    private final UserFacade userFacade;
    private final PostFacade postFacade;
    private final ApplicationEventPublisher eventPublisher;

    @Transactional
    public void execute(Long postId, PostRequest request) {
        User user = userFacade.getCurrentUser();
        Post post = postFacade.getPostById(postId);

        if (!post.getUser().getId().equals(user.getId())) {
            throw CannotUpdatePost.EXCEPTION;
        }

        post.update(
            request.content(),
            request.location(),
            request.objectKey()
        );

        eventPublisher.publishEvent(PostIndexEvent.update(post));
    }
}
