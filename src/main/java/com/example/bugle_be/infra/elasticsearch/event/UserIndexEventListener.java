package com.example.bugle_be.infra.elasticsearch.event;

import com.example.bugle_be.infra.elasticsearch.dlq.DlqConstants;
import com.example.bugle_be.infra.elasticsearch.dlq.DlqEntry;
import com.example.bugle_be.infra.elasticsearch.dlq.ElasticsearchDlqService;
import com.example.bugle_be.infra.elasticsearch.user.service.CreateUserIndexService;
import com.example.bugle_be.infra.elasticsearch.user.service.DeleteUserIndexService;
import lombok.RequiredArgsConstructor;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@RequiredArgsConstructor
public class UserIndexEventListener {

    private final CreateUserIndexService createUserIndexService;
    private final DeleteUserIndexService deleteUserIndexService;
    private final ElasticsearchDlqService dlqService;
    private final RetryTemplate elasticsearchRetryTemplate;

    @Async("elasticsearchAsyncExecutor")
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void handle(UserIndexEvent event) {
        try {
            elasticsearchRetryTemplate.execute(ctx -> {
                switch (event.action()) {
                    case CREATE -> createUserIndexService.execute(event);
                    case DELETE -> deleteUserIndexService.execute(event);
                }
                return null;
            });
        } catch (Exception e) {
            dlqService.enqueue(DlqConstants.USER_DLQ_KEY, DlqEntry.DomainType.USER, event);
        }
    }
}
