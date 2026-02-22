package com.example.bugle_be.domain.auth.service;

import com.example.bugle_be.domain.auth.domain.repository.RefreshTokenRepository;
import com.example.bugle_be.domain.user.domain.User;
import com.example.bugle_be.domain.user.domain.repository.UserRepository;
import com.example.bugle_be.domain.user.facade.UserFacade;
import com.example.bugle_be.infra.s3.event.S3DeleteEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class WithdrawService {

    private final UserFacade userFacade;
    private final UserRepository userRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final ApplicationEventPublisher eventPublisher;

    @Transactional
    public void execute() {
        User user = userFacade.getCurrentUser();

        refreshTokenRepository.deleteById(user.getEmail());
        userRepository.delete(user);

        if (user.isCustomProfileImage()) {
            eventPublisher.publishEvent(new S3DeleteEvent(user.getProfileImageObjectKey()));
        }

        SecurityContextHolder.clearContext();
    }
}
