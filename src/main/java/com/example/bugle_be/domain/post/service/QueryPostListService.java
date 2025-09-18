package com.example.bugle_be.domain.post.service;

import com.example.bugle_be.domain.post.domain.repository.PostRepository;
import com.example.bugle_be.domain.post.presentation.dto.response.QueryPostListResponse;
import com.example.bugle_be.global.dto.TotalPageCountResponse;
import com.example.bugle_be.global.util.PageUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QueryPostListService {

    private final PostRepository postRepository;

    @Transactional(readOnly = true)
    public QueryPostListResponse execute(int page) {
        List<QueryPostListResponse.PostPreviewResponse> posts = postRepository.getAll(page);

        return new QueryPostListResponse(posts);
    }

    public TotalPageCountResponse executeCount() {
        int count = PageUtil.getTotalPageCount(
            postRepository.getAllCount(), PageUtil.POST_DEFAULT_PAGE_SIZE
        );

        return new TotalPageCountResponse(count);
    }
}
