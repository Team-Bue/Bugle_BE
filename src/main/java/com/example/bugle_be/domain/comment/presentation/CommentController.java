package com.example.bugle_be.domain.comment.presentation;

import com.example.bugle_be.domain.comment.presentation.dto.request.CommentRequest;
import com.example.bugle_be.domain.comment.service.CreateCommentService;
import com.example.bugle_be.domain.comment.service.DeleteCommentService;
import com.example.bugle_be.domain.comment.service.UpdateCommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CreateCommentService createCommentService;
    private final UpdateCommentService updateCommentService;
    private final DeleteCommentService deleteCommentService;

    @PostMapping("/{post-id}")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@PathVariable("post-id") Long postId, @RequestBody @Valid CommentRequest request) {
        createCommentService.execute(postId, request);
    }

    @PatchMapping("/{comment-id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable("comment-id") Long commentId, @RequestBody @Valid CommentRequest request) {
        updateCommentService.execute(commentId, request);
    }

    @DeleteMapping("/{comment-id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("comment-id") Long commentId) {
        deleteCommentService.execute(commentId);
    }
}
