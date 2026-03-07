package com.example.bugle_be.infra.elasticsearch.post.service;

import com.example.bugle_be.infra.elasticsearch.event.PostIndexEvent;
import com.example.bugle_be.infra.elasticsearch.post.document.repository.PostDocumentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeletePostIndexService {

    private final PostDocumentRepository postDocumentRepository;

    public void execute(PostIndexEvent event) {
        postDocumentRepository.deleteById(event.postId());
    }
}
