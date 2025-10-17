package com.example.bugle_be.domain.comment.domain.repository;

import com.example.bugle_be.domain.comment.presentation.dto.response.CommentsResponse;

import java.util.List;

public interface CommentRepositoryCustom {

    List<CommentsResponse.CommentResponse> findAll(Long postId);
}
