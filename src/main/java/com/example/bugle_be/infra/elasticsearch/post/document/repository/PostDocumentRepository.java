package com.example.bugle_be.infra.elasticsearch.post.document.repository;

import com.example.bugle_be.infra.elasticsearch.post.document.PostDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface PostDocumentRepository extends ElasticsearchRepository<PostDocument, Long> {
}
