package com.example.bugle_be.domain.post.presentation;

import com.example.bugle_be.domain.post.presentation.dto.request.PostRequest;
import com.example.bugle_be.domain.post.presentation.dto.response.QueryPostListResponse;
import com.example.bugle_be.domain.post.service.CreatePostService;
import com.example.bugle_be.domain.post.service.QueryPostListService;
import com.example.bugle_be.global.dto.TotalPageCountResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {

    private final CreatePostService createPostService;
    private final QueryPostListService queryPostListService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody @Valid PostRequest request) {
        createPostService.execute(request);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public QueryPostListResponse queryPostList(
        @RequestParam(value = "page", required = false, defaultValue = "1") @Positive Integer page
    ) {
        return queryPostListService.execute(page);
    }

    @GetMapping("/count")
    @ResponseStatus(HttpStatus.OK)
    public TotalPageCountResponse queryPostListCount() {
        return queryPostListService.executeCount();
    }
}
