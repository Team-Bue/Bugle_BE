package com.example.bugle_be.infra.elasticsearch.event;

import com.example.bugle_be.infra.elasticsearch.post.service.CreatePostIndexService;
import com.example.bugle_be.infra.elasticsearch.post.service.DeletePostIndexService;
import com.example.bugle_be.infra.elasticsearch.post.service.UpdatePostIndexService;
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

    private final CreatePostIndexService createPostIndexService;
    private final UpdatePostIndexService updatePostIndexService;
    private final DeletePostIndexService deletePostIndexService;

    @Async("elasticsearchAsyncExecutor")
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void handle(PostIndexEvent event) {
        try {
            switch (event.action()) {
                case CREATE -> createPostIndexService.execute(event);
                case UPDATE -> updatePostIndexService.execute(event);
                case DELETE -> deletePostIndexService.execute(event);
            }
        } catch (Exception e) {
            log.error("[ES 인덱싱 실패] action={}, postId={}", event.action(), event.postId(), e);
        }
    }
}
