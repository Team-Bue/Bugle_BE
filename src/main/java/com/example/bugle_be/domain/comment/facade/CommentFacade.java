package com.example.bugle_be.domain.comment.facade;

import com.example.bugle_be.domain.comment.domain.Comment;
import com.example.bugle_be.domain.comment.domain.repository.CommentRepository;
import com.example.bugle_be.domain.comment.exception.CommentNotFound;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CommentFacade {

    private final CommentRepository commentRepository;

    public Comment getCommentById(Long id) {
        return commentRepository.findById(id)
            .orElseThrow(() -> CommentNotFound.EXCEPTION);
    }

    public void validateCommentExists(Long id) {
        if (!commentRepository.existsById(id)) {
            throw CommentNotFound.EXCEPTION;
        }
    }
}
