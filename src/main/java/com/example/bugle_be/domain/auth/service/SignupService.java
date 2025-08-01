package com.example.bugle_be.domain.auth.service;

import com.example.bugle_be.domain.auth.exception.AlreadyAccountIdExists;
import com.example.bugle_be.domain.auth.exception.AlreadyEmailExists;
import com.example.bugle_be.domain.auth.presentation.dto.request.SignupRequest;
import com.example.bugle_be.domain.auth.presentation.dto.response.TokenResponse;
import com.example.bugle_be.domain.user.domain.User;
import com.example.bugle_be.domain.user.domain.repository.UserRepository;
import com.example.bugle_be.global.image.DefaultImageProperties;
import com.example.bugle_be.global.security.jwt.JwtProperties;
import com.example.bugle_be.global.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class SignupService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final JwtProperties jwtProperties;
    private final DefaultImageProperties defaultImage;

    @Transactional
    public TokenResponse execute(SignupRequest request) {
        checkEmail(request.email());
        checkAccountId(request.accountId());

        saveUser(request);

        return generateToken(request.email());
    }

    private void checkEmail(String email) {
        if (userRepository.existsByEmail(email)) {
            throw AlreadyEmailExists.EXCEPTION;
        }
    }

    private void checkAccountId(String accountId) {
        if (userRepository.existsByAccountId(accountId)) {
            throw AlreadyAccountIdExists.EXCEPTION;
        }
    }

    private void saveUser(SignupRequest request) {
        userRepository.save(
            User.builder()
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .accountId(request.accountId())
                .userName(request.userName())
                .profileImageUrl(defaultImage.defaultImageUrl())
                .build()
        );
    }

    private TokenResponse generateToken(String email) {
        return TokenResponse.builder()
            .accessToken(jwtTokenProvider.generateAccessToken(email))
            .refreshToken(jwtTokenProvider.generateRefreshToken(email))
            .accessExp(LocalDateTime.now().plusSeconds(jwtProperties.accessExp()))
            .refreshExp(LocalDateTime.now().plusSeconds(jwtProperties.refreshExp()))
            .build();
    }
}
