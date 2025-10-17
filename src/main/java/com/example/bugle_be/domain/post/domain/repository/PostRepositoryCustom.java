package com.example.bugle_be.domain.post.domain.repository;

import com.example.bugle_be.domain.post.presentation.dto.response.PostsResponse;
import com.example.bugle_be.domain.search.presentation.dto.SearchType;
import com.example.bugle_be.domain.search.presentation.dto.response.PostSearchResponse;

import java.util.List;

public interface PostRepositoryCustom {

    List<PostsResponse.PostPreviewResponse> findAll(int page);

    Long countAll();

    List<PostSearchResponse.PostResponse> findAllByTypeAndKeyword(int page, SearchType type, String keyword);

    Long findAllByTypeAndKeywordCount(SearchType type, String keyword);
}
