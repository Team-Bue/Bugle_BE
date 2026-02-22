package com.example.bugle_be.domain.follow.service;

import com.example.bugle_be.domain.follow.domain.repository.FollowRepository;
import com.example.bugle_be.domain.follow.exception.NotFollowing;
import com.example.bugle_be.domain.user.domain.User;
import com.example.bugle_be.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UnFollowService {

    private final UserFacade userFacade;
    private final FollowRepository followRepository;

    @Transactional
    public void execute(Long followingId) {
        User follower = userFacade.getCurrentUser();
        User following = userFacade.getUserById(followingId);

        int deletedCount = followRepository.deleteByFollowerAndFollowing(follower, following);
        if (deletedCount == 0) {
            throw NotFollowing.EXCEPTION;
        }
    }
}
