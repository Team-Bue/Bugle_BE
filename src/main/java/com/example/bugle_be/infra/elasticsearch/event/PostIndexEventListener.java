package com.example.bugle_be.infra.elasticsearch.event;

import com.example.bugle_be.infra.elasticsearch.dlq.DlqConstants;
import com.example.bugle_be.infra.elasticsearch.dlq.DlqEntry;
import com.example.bugle_be.infra.elasticsearch.dlq.ElasticsearchDlqService;
import com.example.bugle_be.infra.elasticsearch.post.service.DeletePostIndexService;
import com.example.bugle_be.infra.elasticsearch.post.service.SavePostIndexService;
import lombok.RequiredArgsConstructor;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@RequiredArgsConstructor
public class PostIndexEventListener {

    private final SavePostIndexService savePostIndexService;
    private final DeletePostIndexService deletePostIndexService;
    private final ElasticsearchDlqService dlqService;
    private final RetryTemplate elasticsearchRetryTemplate;

    @Async("elasticsearchAsyncExecutor")
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void handle(PostIndexEvent event) {
        try {
            elasticsearchRetryTemplate.execute(ctx -> {
                switch (event.action()) {
                    case CREATE, UPDATE -> savePostIndexService.execute(event);
                    case DELETE -> deletePostIndexService.execute(event);
                }
                return null;
            });
        } catch (Exception e) {
            dlqService.enqueue(DlqConstants.POST_DLQ_KEY, DlqEntry.DomainType.POST, event);
        }
    }
}
