package com.example.bugle_be.infra.elasticsearch.event;

import com.example.bugle_be.infra.elasticsearch.user.service.CreateUserIndexService;
import com.example.bugle_be.infra.elasticsearch.user.service.DeleteUserIndexService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@RequiredArgsConstructor
public class UserIndexEventListener {

    private final CreateUserIndexService createUserIndexService;
    private final DeleteUserIndexService deleteUserIndexService;

    @Async("elasticsearchAsyncExecutor")
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void handle(UserIndexEvent event) {
        switch (event.action()) {
            case CREATE -> createUserIndexService.execute(event);
            case DELETE -> deleteUserIndexService.execute(event);
        }
    }
}
