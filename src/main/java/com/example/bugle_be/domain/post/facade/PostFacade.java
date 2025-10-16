package com.example.bugle_be.domain.post.facade;

import com.example.bugle_be.domain.post.domain.Post;
import com.example.bugle_be.domain.post.domain.repository.PostRepository;
import com.example.bugle_be.domain.post.exception.PostNotFound;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PostFacade {

    private final PostRepository postRepository;

    public Post getPostById(Long id) {
        return postRepository.findById(id)
            .orElseThrow(() -> PostNotFound.EXCEPTION);
    }

    public void validatePostExists(Long id) {
        if (!postRepository.existsById(id)) {
            throw PostNotFound.EXCEPTION;
        }
    }
}
