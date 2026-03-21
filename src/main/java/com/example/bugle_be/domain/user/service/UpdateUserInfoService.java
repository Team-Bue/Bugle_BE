package com.example.bugle_be.domain.user.service;

import com.example.bugle_be.domain.user.domain.User;
import com.example.bugle_be.domain.user.domain.repository.UserRepository;
import com.example.bugle_be.domain.user.exception.AccountIdAlreadyExists;
import com.example.bugle_be.domain.user.facade.UserFacade;
import com.example.bugle_be.domain.user.presentation.dto.request.UserInfoRequest;
import com.example.bugle_be.infra.elasticsearch.event.UserIndexEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UpdateUserInfoService {

    private final UserFacade userFacade;
    private final UserRepository userRepository;
    private final ApplicationEventPublisher eventPublisher;

    @Transactional
    public void execute(UserInfoRequest request) {
        User user = userFacade.getCurrentUser();
        UserInfoRequest resolved = request.resolve(user);

        if (!user.getAccountId().equals(resolved.accountId())
                && userRepository.existsByAccountId(resolved.accountId())) {
            throw AccountIdAlreadyExists.EXCEPTION;
        }

        user.update(resolved.accountId(), resolved.userName(), resolved.profileImageObjectKey());

        eventPublisher.publishEvent(UserIndexEvent.update(user));
    }
}
