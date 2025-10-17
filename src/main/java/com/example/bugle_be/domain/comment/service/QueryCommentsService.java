package com.example.bugle_be.domain.comment.service;

import com.example.bugle_be.domain.comment.domain.repository.CommentRepository;
import com.example.bugle_be.domain.comment.presentation.dto.response.CommentsResponse;
import com.example.bugle_be.domain.post.domain.Post;
import com.example.bugle_be.domain.post.facade.PostFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QueryCommentsService {

    private final PostFacade postFacade;
    private final CommentRepository commentRepository;

    @Transactional(readOnly = true)
    public CommentsResponse execute(Long postId) {
        Post post = postFacade.getPostById(postId);
        List<CommentsResponse.CommentResponse> comments = commentRepository.findAll(postId);

        return new CommentsResponse(comments);
    }
}
