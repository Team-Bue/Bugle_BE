package com.example.bugle_be.domain.auth.service;

import com.example.bugle_be.domain.auth.exception.AlreadyAccountIdExists;
import com.example.bugle_be.domain.auth.exception.AlreadyEmailExists;
import com.example.bugle_be.domain.auth.presentation.dto.request.SignupRequest;
import com.example.bugle_be.domain.auth.presentation.dto.response.TokenResponse;
import com.example.bugle_be.domain.mail.service.MailService;
import com.example.bugle_be.domain.user.domain.User;
import com.example.bugle_be.domain.user.domain.repository.UserRepository;
import com.example.bugle_be.global.security.jwt.JwtTokenProvider;
import com.example.bugle_be.infra.elasticsearch.event.UserIndexEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SignupService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final MailService mailService;
    private final ApplicationEventPublisher eventPublisher;

    @Transactional
    public TokenResponse execute(SignupRequest request) {
        mailService.validateToken(request.email(), request.token());

        checkDuplicate(request);
        User user = saveUser(request);

        eventPublisher.publishEvent(UserIndexEvent.create(user));

        return jwtTokenProvider.createToken(request.email());
    }

    private void checkDuplicate(SignupRequest request) {
        if (userRepository.existsByEmail(request.email())) {
            throw AlreadyEmailExists.EXCEPTION;
        }
        if (userRepository.existsByAccountId(request.accountId())) {
            throw AlreadyAccountIdExists.EXCEPTION;
        }
    }

    private User saveUser(SignupRequest request) {
        return userRepository.save(
            User.builder()
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .accountId(request.accountId())
                .userName(request.userName())
                .build()
        );
    }
}
