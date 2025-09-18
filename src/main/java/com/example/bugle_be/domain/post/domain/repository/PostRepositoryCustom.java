package com.example.bugle_be.domain.post.domain.repository;

import com.example.bugle_be.domain.post.presentation.dto.response.QueryPostListResponse;
import com.example.bugle_be.domain.search.presentation.dto.SearchType;
import com.example.bugle_be.domain.search.presentation.dto.response.PostSearchResponse;

import java.util.List;

public interface PostRepositoryCustom {

    List<QueryPostListResponse.PostPreviewResponse> getAll(int page);

    List<PostSearchResponse.PostResponse> getAllByTypeAndKeyword(SearchType type, String keyword);
}
