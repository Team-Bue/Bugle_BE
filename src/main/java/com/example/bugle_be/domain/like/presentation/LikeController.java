package com.example.bugle_be.domain.like.presentation;

import com.example.bugle_be.domain.like.service.LikeService;
import com.example.bugle_be.domain.like.service.UnLikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/likes")
@RequiredArgsConstructor
public class LikeController {

    private final LikeService likeService;
    private final UnLikeService unLikeService;

    @PostMapping("/{post-id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void like(@PathVariable("post-id") Long postId) {
        likeService.execute(postId);
    }

    @DeleteMapping("/{post-id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void unlike(@PathVariable("post-id") Long postId) {
        unLikeService.execute(postId);
    }
}
