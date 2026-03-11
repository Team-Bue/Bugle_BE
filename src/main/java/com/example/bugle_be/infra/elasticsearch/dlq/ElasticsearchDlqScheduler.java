package com.example.bugle_be.infra.elasticsearch.dlq;

import com.example.bugle_be.infra.elasticsearch.event.PostIndexEvent;
import com.example.bugle_be.infra.elasticsearch.event.UserIndexEvent;
import com.example.bugle_be.infra.elasticsearch.post.service.DeletePostIndexService;
import com.example.bugle_be.infra.elasticsearch.post.service.SavePostIndexService;
import com.example.bugle_be.infra.elasticsearch.user.service.CreateUserIndexService;
import com.example.bugle_be.infra.elasticsearch.user.service.DeleteUserIndexService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ElasticsearchDlqScheduler {

    private final ElasticsearchDlqService dlqService;
    private final SavePostIndexService savePostIndexService;
    private final DeletePostIndexService deletePostIndexService;
    private final CreateUserIndexService createUserIndexService;
    private final DeleteUserIndexService deleteUserIndexService;
    private final RetryTemplate elasticsearchRetryTemplate;
    private final ObjectMapper objectMapper;

    @Scheduled(fixedDelay = 1_800_000)
    public void retryDlq() {
        processEntries(DlqConstants.POST_DLQ_KEY);
        processEntries(DlqConstants.USER_DLQ_KEY);
    }

    private void processEntries(String key) {
        Long size = dlqService.size(key);
        if (size == null || size == 0) return;

        for (int i = 0; i < size; i++) {
            DlqEntry entry = dlqService.dequeue(key);
            if (entry == null) break;

            if (entry.isExhausted()) {
                log.error("[ES DLQ 재처리 포기] 최대 재시도 횟수 초과. domainType={}, retryCount={}, payload={}",
                    entry.domainType(), entry.retryCount(), entry.payload());
                continue;
            }

            try {
                processEvent(entry);
            } catch (Exception e) {
                log.error("[ES DLQ 재처리 최종 실패] domainType={}, retryCount={}, payload={}",
                    entry.domainType(), entry.retryCount(), entry.payload(), e);
                dlqService.requeue(key, entry);
            }
        }
    }

    private void processEvent(DlqEntry entry) throws Exception {
        switch (entry.domainType()) {
            case POST -> {
                PostIndexEvent event = objectMapper.readValue(entry.payload(), PostIndexEvent.class);
                elasticsearchRetryTemplate.execute(ctx -> {
                    switch (event.action()) {
                        case CREATE, UPDATE -> savePostIndexService.execute(event);
                        case DELETE -> deletePostIndexService.execute(event);
                    }
                    return null;
                });
            }
            case USER -> {
                UserIndexEvent event = objectMapper.readValue(entry.payload(), UserIndexEvent.class);
                elasticsearchRetryTemplate.execute(ctx -> {
                    switch (event.action()) {
                        case CREATE -> createUserIndexService.execute(event);
                        case DELETE -> deleteUserIndexService.execute(event);
                    }
                    return null;
                });
            }
        }
    }
}
