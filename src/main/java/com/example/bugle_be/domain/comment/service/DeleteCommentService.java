package com.example.bugle_be.domain.comment.service;

import com.example.bugle_be.domain.comment.domain.Comment;
import com.example.bugle_be.domain.comment.domain.repository.CommentRepository;
import com.example.bugle_be.domain.comment.exception.CannotDeleteComment;
import com.example.bugle_be.domain.comment.facade.CommentFacade;
import com.example.bugle_be.domain.user.domain.User;
import com.example.bugle_be.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DeleteCommentService {

    private final CommentFacade commentFacade;
    private final UserFacade userFacade;
    private final CommentRepository commentRepository;

    @Transactional
    public void execute(Long commentId) {
        Comment comment = commentFacade.getCommentById(commentId);
        User user = userFacade.getCurrentUser();

        if (!comment.getUser().getId().equals(user.getId())) {
            throw CannotDeleteComment.EXCEPTION;
        }

        commentRepository.delete(comment);
    }
}
