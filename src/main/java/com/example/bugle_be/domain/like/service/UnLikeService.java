package com.example.bugle_be.domain.like.service;

import com.example.bugle_be.domain.like.domain.repository.LikeRepository;
import com.example.bugle_be.domain.like.exception.NotLiked;
import com.example.bugle_be.domain.post.domain.Post;
import com.example.bugle_be.domain.post.facade.PostFacade;
import com.example.bugle_be.domain.user.domain.User;
import com.example.bugle_be.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UnLikeService {

    private final UserFacade userFacade;
    private final PostFacade postFacade;
    private final LikeRepository likeRepository;

    @Transactional
    public void execute(Long postId) {
        User user = userFacade.getCurrentUser();
        Post post = postFacade.getPostById(postId);

        int deletedCount = likeRepository.deleteByUserAndPost(user, post);
        if (deletedCount == 0) {
            throw NotLiked.EXCEPTION;
        }
    }
}
