package com.example.bugle_be.domain.post.service;

import com.example.bugle_be.domain.post.domain.repository.PostRepository;
import com.example.bugle_be.domain.post.presentation.dto.response.QueryPostListResponse;
import com.example.bugle_be.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QueryPostListService {

    private final UserFacade userFacade;
    private final PostRepository postRepository;

    @Transactional(readOnly = true)
    public QueryPostListResponse execute() {
        userFacade.getCurrentUser();

        List<QueryPostListResponse.PostPreviewResponse> posts = postRepository.findAll()
            .stream()
            .map(post -> QueryPostListResponse.PostPreviewResponse.builder()
                .id(post.getId())
                .accountId(post.getUser().getAccountId())
                .profileImageUrl(post.getUser().getProfileImageUrl())
                .country(post.getCountry())
                .region(post.getRegion())
                .fileUrl(post.getFileUrl())
                .content(post.getContent())
                .build())
            .toList();

        return new QueryPostListResponse(posts);
    }
}
