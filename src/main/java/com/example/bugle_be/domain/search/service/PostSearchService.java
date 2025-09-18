package com.example.bugle_be.domain.search.service;

import com.example.bugle_be.domain.post.domain.repository.PostRepository;
import com.example.bugle_be.domain.search.presentation.dto.request.PostSearchRequest;
import com.example.bugle_be.domain.search.presentation.dto.response.PostSearchResponse;
import com.example.bugle_be.global.dto.TotalPageCountResponse;
import com.example.bugle_be.global.util.PageUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostSearchService {

    private final PostRepository postRepository;

    @Transactional(readOnly = true)
    public PostSearchResponse execute(int page, PostSearchRequest request) {
        List<PostSearchResponse.PostResponse> posts = postRepository.getAllByTypeAndKeyword(page, request.type(), request.keyword());

        return new PostSearchResponse(posts);
    }

    public TotalPageCountResponse executeCount(PostSearchRequest request) {
        int count = PageUtil.getTotalPageCount(
            postRepository.getAllByTypeAndKeywordCount(request.type(), request.keyword()), PageUtil.POST_DEFAULT_PAGE_SIZE
        );

        return new TotalPageCountResponse(count);
    }
}
