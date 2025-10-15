package com.example.bugle_be.domain.comment.presentation;

import com.example.bugle_be.domain.comment.presentation.dto.request.CommentRequest;
import com.example.bugle_be.domain.comment.service.CreateCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CreateCommentService createCommentService;

    @PostMapping("/{post-id}")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@PathVariable("post-id") Long postId, @RequestBody CommentRequest request) {
        createCommentService.execute(postId, request);
    }
}
