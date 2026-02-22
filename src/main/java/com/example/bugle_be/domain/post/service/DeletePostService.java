package com.example.bugle_be.domain.post.service;

import com.example.bugle_be.domain.post.domain.Post;
import com.example.bugle_be.domain.post.domain.repository.PostRepository;
import com.example.bugle_be.domain.post.exception.CannotDeletePost;
import com.example.bugle_be.domain.post.facade.PostFacade;
import com.example.bugle_be.domain.user.domain.User;
import com.example.bugle_be.domain.user.facade.UserFacade;
import com.example.bugle_be.infra.s3.event.S3DeleteEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DeletePostService {

    private final UserFacade userFacade;
    private final PostFacade postFacade;
    private final PostRepository postRepository;
    private final ApplicationEventPublisher eventPublisher;

    @Transactional
    public void execute(Long postId) {
        User user = userFacade.getCurrentUser();
        Post post = postFacade.getPostById(postId);

        if (!post.getUser().getId().equals(user.getId())) {
            throw CannotDeletePost.EXCEPTION;
        }

        postRepository.delete(post);
        eventPublisher.publishEvent(new S3DeleteEvent(post.getObjectKey()));
    }
}
