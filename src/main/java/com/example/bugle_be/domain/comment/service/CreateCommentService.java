package com.example.bugle_be.domain.comment.service;

import com.example.bugle_be.domain.comment.domain.Comment;
import com.example.bugle_be.domain.comment.domain.repository.CommentRepository;
import com.example.bugle_be.domain.post.domain.Post;
import com.example.bugle_be.domain.post.domain.repository.PostRepository;
import com.example.bugle_be.domain.post.exception.PostNotFound;
import com.example.bugle_be.domain.post.presentation.dto.request.PostRequest;
import com.example.bugle_be.domain.user.domain.User;
import com.example.bugle_be.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CreateCommentService {

    private final UserFacade userFacade;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    @Transactional
    public void execute(Long postId, PostRequest request) {
        Post post = postRepository.findById(postId)
            .orElseThrow(() -> PostNotFound.EXCEPTION);
        User user = userFacade.getCurrentUser();

        commentRepository.save(
            Comment.builder()
                .content(request.content())
                .post(post)
                .user(user)
                .build()
        );
    }
}
