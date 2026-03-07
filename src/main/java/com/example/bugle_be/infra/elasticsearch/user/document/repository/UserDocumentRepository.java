package com.example.bugle_be.infra.elasticsearch.user.document.repository;

import com.example.bugle_be.infra.elasticsearch.user.document.UserDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface UserDocumentRepository extends ElasticsearchRepository<UserDocument, Long> {
}
