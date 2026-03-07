package com.example.bugle_be.infra.elasticsearch.event;

import com.example.bugle_be.infra.elasticsearch.post.service.DeletePostIndexService;
import com.example.bugle_be.infra.elasticsearch.post.service.SavePostIndexService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Slf4j
@Component
@RequiredArgsConstructor
public class PostIndexEventListener {

    private final SavePostIndexService savePostIndexService;
    private final DeletePostIndexService deletePostIndexService;

    @Async("elasticsearchAsyncExecutor")
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void handle(PostIndexEvent event) {
        try {
            switch (event.action()) {
                case CREATE, UPDATE -> savePostIndexService.execute(event);
                case DELETE -> deletePostIndexService.execute(event);
            }
        } catch (Exception e) {
            log.error("[ES 인덱싱 실패] action={}, postId={}", event.action(), event.postId(), e);
        }
    }
}
