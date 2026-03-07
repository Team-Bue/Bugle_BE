package com.example.bugle_be.infra.elasticsearch.event;

import com.example.bugle_be.infra.elasticsearch.post.service.CreatePostIndexService;
import com.example.bugle_be.infra.elasticsearch.post.service.UpdatePostIndexService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@RequiredArgsConstructor
public class PostIndexEventListener {

    private final CreatePostIndexService createPostIndexService;
    private final UpdatePostIndexService updatePostIndexService;

    @Async("elasticsearchAsyncExecutor")
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void handle(PostIndexEvent event) {
        switch (event.action()) {
            case CREATE -> createPostIndexService.execute(event);
            case UPDATE -> updatePostIndexService.execute(event);
        }
    }
}
