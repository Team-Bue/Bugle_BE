package com.example.bugle_be.domain.post.domain.repository;

import com.example.bugle_be.domain.post.presentation.dto.response.PostDetailResponse;
import com.example.bugle_be.domain.post.presentation.dto.response.PostsResponse;
import com.example.bugle_be.domain.search.presentation.dto.SearchType;
import com.example.bugle_be.domain.search.presentation.dto.response.PostSearchResponse;

import java.util.List;
import java.util.Optional;

public interface PostRepositoryCustom {

    List<PostsResponse.PostPreviewResponse> findAll(int page);

    Optional<PostDetailResponse> findDetailById(Long id);

    Long countAll();

    List<PostSearchResponse.PostResponse> findAllByTypeAndKeyword(int page, SearchType type, String keyword);

    Long countByTypeAndKeywordContaining(SearchType type, String keyword);
}
