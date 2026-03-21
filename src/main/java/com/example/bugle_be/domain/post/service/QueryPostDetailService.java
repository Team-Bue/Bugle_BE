package com.example.bugle_be.domain.post.service;

import com.example.bugle_be.domain.post.domain.repository.PostRepository;
import com.example.bugle_be.domain.post.exception.PostNotFound;
import com.example.bugle_be.domain.post.presentation.dto.response.PostDetailResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class QueryPostDetailService {

    private final PostRepository postRepository;

    @Transactional(readOnly = true)
    public PostDetailResponse execute(Long postId) {
        return postRepository.findDetailById(postId)
            .orElseThrow(() -> PostNotFound.EXCEPTION);
    }
}