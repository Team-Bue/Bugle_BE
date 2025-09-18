package com.example.bugle_be.domain.search.presentation;

import com.example.bugle_be.domain.search.presentation.dto.request.PostSearchRequest;
import com.example.bugle_be.domain.search.presentation.dto.request.UserSearchRequest;
import com.example.bugle_be.domain.search.presentation.dto.response.PostSearchResponse;
import com.example.bugle_be.domain.search.presentation.dto.response.UserSearchResponse;
import com.example.bugle_be.domain.search.service.PostSearchService;
import com.example.bugle_be.domain.search.service.UserSearchService;
import com.example.bugle_be.global.dto.TotalPageCountResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/search")
@RequiredArgsConstructor
public class SearchController {

    private final UserSearchService userSearchService;
    private final PostSearchService postSearchService;

    @GetMapping("/users")
    public UserSearchResponse userSearch(
        @RequestParam(value = "page", required = false, defaultValue = "1") @Positive Integer page,
        @Valid @ModelAttribute UserSearchRequest request
    ) {
        return userSearchService.execute(page, request);
    }

    @GetMapping("/posts")
    public PostSearchResponse postSearch(
        @RequestParam(value = "page", required = false, defaultValue = "1") @Positive Integer page,
        @Valid @ModelAttribute PostSearchRequest request
    ) {
        return postSearchService.execute(page, request);
    }

    @GetMapping("/posts/count")
    public TotalPageCountResponse postSearchCount(
        @Valid @ModelAttribute PostSearchRequest request
    ) {
        return postSearchService.executeCount(request);
    }
}
