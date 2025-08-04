package com.example.bugle_be.domain.post.service;

import com.example.bugle_be.domain.post.domain.Post;
import com.example.bugle_be.domain.post.domain.repository.PostRepository;
import com.example.bugle_be.domain.post.presentation.dto.request.PostRequest;
import com.example.bugle_be.domain.user.domain.User;
import com.example.bugle_be.domain.user.facacde.UserFacade;
import com.example.bugle_be.infra.s3.service.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CreatePostService {

    private final UserFacade userFacade;
    private final PostRepository postRepository;

    @Transactional
    public void execute(PostRequest request) {
        User user = userFacade.getCurrentUser();

        postRepository.save(
            Post.builder()
                .title(request.title())
                .content(request.content())
                .country(request.country())
                .region(request.region())
                .imageUrl(request.objectKey())
                .user(user)
                .build()
        );
    }
}
