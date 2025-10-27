package com.example.bugle_be.domain.follow.service;

import com.example.bugle_be.domain.follow.domain.Follow;
import com.example.bugle_be.domain.follow.domain.repository.FollowRepository;
import com.example.bugle_be.domain.follow.exception.AlreadyFollowed;
import com.example.bugle_be.domain.follow.exception.CannotFollowYourself;
import com.example.bugle_be.domain.notification.message.NotificationMessage;
import com.example.bugle_be.domain.notification.service.NotificationService;
import com.example.bugle_be.domain.user.domain.User;
import com.example.bugle_be.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class FollowService {

    private final UserFacade userFacade;
    private final FollowRepository followRepository;
    private final NotificationService notificationService;

    @Transactional
    public void execute(Long followingId) {
        User follower = userFacade.getCurrentUser();
        User following = userFacade.getUserById(followingId);

        if (follower.getId().equals(followingId)) {
            throw CannotFollowYourself.EXCEPTION;
        }

        try {
            followRepository.save(
                Follow.builder()
                    .follower(follower)
                    .following(following)
                    .build()
            );
        } catch (DataIntegrityViolationException e) {
            throw AlreadyFollowed.EXCEPTION;
        }

        notificationService.execute(
            following,
            follower.getAccountId() + NotificationMessage.FOLLOW.getMessage()
        );
    }
}
