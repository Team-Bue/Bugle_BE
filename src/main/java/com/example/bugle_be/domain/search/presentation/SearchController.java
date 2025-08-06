package com.example.bugle_be.domain.search.presentation;

import com.example.bugle_be.domain.search.presentation.dto.request.PostSearchRequest;
import com.example.bugle_be.domain.search.presentation.dto.request.UserSearchRequest;
import com.example.bugle_be.domain.search.presentation.dto.response.PostSearchResponse;
import com.example.bugle_be.domain.search.presentation.dto.response.UserSearchResponse;
import com.example.bugle_be.domain.search.service.PostSearchService;
import com.example.bugle_be.domain.search.service.UserSearchService;
import jakarta.validation.Valid;
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
        @Valid @ModelAttribute UserSearchRequest request
    ) {
        return userSearchService.execute(request);
    }

    @GetMapping("/posts")
    public PostSearchResponse postSearch(
        @Valid @ModelAttribute PostSearchRequest request
    ) {
        return postSearchService.execute(request);
    }
}
