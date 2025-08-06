package com.example.bugle_be.domain.search.service;

import com.example.bugle_be.domain.post.domain.repository.PostRepository;
import com.example.bugle_be.domain.search.presentation.dto.request.PostSearchRequest;
import com.example.bugle_be.domain.search.presentation.dto.response.PostSearchResponse;
import com.example.bugle_be.domain.user.facacde.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostSearchService {

    private final UserFacade userFacade;
    private final PostRepository postRepository;

    @Transactional(readOnly = true)
    public PostSearchResponse execute(PostSearchRequest request) {
        userFacade.getCurrentUser();
        List<PostSearchResponse.PostResponse> posts = postRepository.getAllByTypeAndKeyword(request.type(), request.keyword());

        return new PostSearchResponse(posts);
    }
}
