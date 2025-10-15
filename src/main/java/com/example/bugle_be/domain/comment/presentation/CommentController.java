package com.example.bugle_be.domain.comment.presentation;

import com.example.bugle_be.domain.comment.presentation.dto.request.CommentRequest;
import com.example.bugle_be.domain.comment.service.CreateCommentService;
import com.example.bugle_be.domain.comment.service.UpdateCommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CreateCommentService createCommentService;
    private final UpdateCommentService updateCommentService;

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
}
