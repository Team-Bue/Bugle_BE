package com.example.bugle_be.domain.auth.service;

import com.example.bugle_be.domain.auth.presentation.dto.request.PasswordResetRequest;
import com.example.bugle_be.domain.mail.service.MailService;
import com.example.bugle_be.domain.user.domain.User;
import com.example.bugle_be.domain.user.domain.repository.UserRepository;
import com.example.bugle_be.domain.user.exception.UserNotFound;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PasswordResetService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final MailService mailService;

    @Transactional
    public void execute(PasswordResetRequest request) {
        User user = userRepository.findByEmail(request.email())
            .orElseThrow(() -> UserNotFound.EXCEPTION);

        mailService.validateToken(request.email(), request.token());

        user.changePassword(passwordEncoder.encode(request.newPassword()));
    }
}
