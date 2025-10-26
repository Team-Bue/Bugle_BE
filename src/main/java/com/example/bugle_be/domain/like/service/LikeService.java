package com.example.bugle_be.domain.like.service;

import com.example.bugle_be.domain.like.domain.Like;
import com.example.bugle_be.domain.like.domain.repository.LikeRepository;
import com.example.bugle_be.domain.like.exception.AlreadyLiked;
import com.example.bugle_be.domain.notification.message.NotificationMessage;
import com.example.bugle_be.domain.notification.service.NotificationService;
import com.example.bugle_be.domain.post.domain.Post;
import com.example.bugle_be.domain.post.facade.PostFacade;
import com.example.bugle_be.domain.user.domain.User;
import com.example.bugle_be.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LikeService {

    private final PostFacade postFacade;
    private final UserFacade userFacade;
    private final LikeRepository likeRepository;
    private final NotificationService notificationService;

    @Transactional
    public void execute(Long postId) {
        User user = userFacade.getCurrentUser();
        Post post = postFacade.getPostById(postId);

        try {
            likeRepository.save(
                Like.builder()
                    .user(user)
                    .post(post)
                    .build()
            );

            notificationService.execute(
                post.getUser(),
                user.getAccountId()+ NotificationMessage.LIKE.getMessage()
                );
        } catch (DataIntegrityViolationException e) {
            throw AlreadyLiked.EXCEPTION;
        }
    }
}
