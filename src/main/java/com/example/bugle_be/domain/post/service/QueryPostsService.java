package com.example.bugle_be.domain.post.service;

import com.example.bugle_be.domain.post.domain.repository.PostRepository;
import com.example.bugle_be.domain.post.presentation.dto.response.PostsResponse;
import com.example.bugle_be.global.dto.TotalPageCountResponse;
import com.example.bugle_be.global.util.PageUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QueryPostsService {

    private final PostRepository postRepository;

    @Transactional(readOnly = true)
    public PostsResponse execute(int page) {
        List<PostsResponse.PostPreviewResponse> posts = postRepository.getAll(page);

        return new PostsResponse(posts);
    }

    @Transactional(readOnly = true)
    public TotalPageCountResponse executeCount() {
        int count = PageUtil.getTotalPageCount(
            postRepository.getAllCount(), PageUtil.POST_DEFAULT_PAGE_SIZE
        );

        return new TotalPageCountResponse(count);
    }
}
