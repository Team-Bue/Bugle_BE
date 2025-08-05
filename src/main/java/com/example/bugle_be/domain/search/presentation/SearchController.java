package com.example.bugle_be.domain.search.presentation;

import com.example.bugle_be.domain.search.presentation.dto.SearchType;
import com.example.bugle_be.domain.search.presentation.dto.response.PostSearchResponse;
import com.example.bugle_be.domain.search.presentation.dto.response.UserSearchResponse;
import com.example.bugle_be.domain.search.service.PostSearchService;
import com.example.bugle_be.domain.search.service.UserSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/search")
@RequiredArgsConstructor
public class SearchController {

    private final UserSearchService userSearchService;
    private final PostSearchService postSearchService;

    @GetMapping("/users")
    public UserSearchResponse userSearch(
        @RequestParam(value = "keyword") String keyword
    ) {
        return userSearchService.execute(keyword);
    }

    @GetMapping("/posts")
    public PostSearchResponse postSearch(
        @RequestParam(value = "type") SearchType type,
        @RequestParam(value = "keyword") String keyword
    ) {
        return postSearchService.execute(type, keyword);
    }
}
