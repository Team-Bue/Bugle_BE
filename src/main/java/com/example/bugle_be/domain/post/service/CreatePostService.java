package com.example.bugle_be.domain.post.service;

import com.example.bugle_be.domain.post.domain.Post;
import com.example.bugle_be.domain.post.domain.repository.PostRepository;
import com.example.bugle_be.domain.post.presentation.dto.request.PostRequest;
import com.example.bugle_be.domain.user.domain.User;
import com.example.bugle_be.domain.user.facade.UserFacade;
import com.example.bugle_be.infra.s3.service.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CreatePostService {

    private final UserFacade userFacade;
    private final PostRepository postRepository;
    private final S3Service s3Service;

    @Transactional
    public void execute(PostRequest request) {
        User user = userFacade.getCurrentUser();

        postRepository.save(
            Post.builder()
                .content(request.content())
                .country(request.country())
                .region(request.region())
                .fileUrl(s3Service.generateUrl(request.objectKey()))
                .user(user)
                .build()
        );
    }
}
