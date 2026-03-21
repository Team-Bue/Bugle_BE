package com.example.bugle_be.domain.post.presentation;

import com.example.bugle_be.domain.post.presentation.dto.request.PostRequest;
import com.example.bugle_be.domain.post.presentation.dto.response.PostDetailResponse;
import com.example.bugle_be.domain.post.presentation.dto.response.PostsResponse;
import com.example.bugle_be.domain.post.service.CreatePostService;
import com.example.bugle_be.domain.post.service.DeletePostService;
import com.example.bugle_be.domain.post.service.QueryPostDetailService;
import com.example.bugle_be.domain.post.service.QueryPostsService;
import com.example.bugle_be.domain.post.service.UpdatePostService;
import com.example.bugle_be.global.dto.TotalPageCountResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Validated
@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {

    private final CreatePostService createPostService;
    private final UpdatePostService updatePostService;
    private final DeletePostService deletePostService;
    private final QueryPostsService queryPostsService;
    private final QueryPostDetailService queryPostDetailService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody @Valid PostRequest request) {
        createPostService.execute(request);
    }

    @PatchMapping("/{post-id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable("post-id") Long postId, @RequestBody @Valid PostRequest request) {
        updatePostService.execute(postId, request);
    }

    @DeleteMapping("/{post-id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("post-id") Long postId) {
        deletePostService.execute(postId);
    }

    @GetMapping("/{post-id}")
    @ResponseStatus(HttpStatus.OK)
    public PostDetailResponse queryDetail(@PathVariable("post-id") Long postId) {
        return queryPostDetailService.execute(postId);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public PostsResponse queryAll(
        @RequestParam(value = "page", required = false, defaultValue = "1") @Positive int page
    ) {
        return queryPostsService.execute(page);
    }

    @GetMapping("/count")
    @ResponseStatus(HttpStatus.OK)
    public TotalPageCountResponse queryCount() {
        return queryPostsService.executeCount();
    }
}
