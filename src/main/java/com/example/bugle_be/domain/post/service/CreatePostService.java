package com.example.bugle_be.domain.post.service;

import com.example.bugle_be.domain.post.domain.Post;
import com.example.bugle_be.domain.post.domain.repository.PostRepository;
import com.example.bugle_be.domain.post.presentation.dto.request.PostRequest;
import com.example.bugle_be.domain.user.domain.User;
import com.example.bugle_be.domain.user.facade.UserFacade;
import com.example.bugle_be.infra.elasticsearch.event.IndexAction;
import com.example.bugle_be.infra.elasticsearch.event.PostIndexEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CreatePostService {

    private final UserFacade userFacade;
    private final PostRepository postRepository;
    private final ApplicationEventPublisher eventPublisher;

    @Transactional
    public void execute(PostRequest request) {
        User user = userFacade.getCurrentUser();

        Post post = postRepository.save(
            Post.builder()
                .content(request.content())
                .location(request.location())
                .objectKey(request.objectKey())
                .user(user)
                .build()
        );

        eventPublisher.publishEvent(new PostIndexEvent(
            post.getId(),
            post.getContent(),
            post.getLocation(),
            post.getObjectKey(),
            IndexAction.CREATE
        ));
    }
}
