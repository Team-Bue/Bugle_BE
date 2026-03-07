package com.example.bugle_be.infra.elasticsearch.user.service;

import com.example.bugle_be.infra.elasticsearch.event.UserIndexEvent;
import com.example.bugle_be.infra.elasticsearch.user.document.repository.UserDocumentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteUserIndexService {

    private final UserDocumentRepository userDocumentRepository;

    public void execute(UserIndexEvent event) {
        userDocumentRepository.deleteById(event.userId());
    }
}
