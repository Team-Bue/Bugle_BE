package com.example.bugle_be.domain.auth.service;

import com.example.bugle_be.domain.auth.exception.AccountIdNotFound;
import com.example.bugle_be.domain.auth.exception.EmailNotFound;
import com.example.bugle_be.domain.auth.exception.LoginIdentifierNotProvided;
import com.example.bugle_be.domain.auth.exception.PasswordMisMatch;
import com.example.bugle_be.domain.auth.presentation.dto.request.LoginRequest;
import com.example.bugle_be.domain.auth.presentation.dto.response.TokenResponse;
import com.example.bugle_be.domain.user.domain.User;
import com.example.bugle_be.domain.user.domain.repository.UserRepository;
import com.example.bugle_be.global.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;

    @Transactional(readOnly = true)
    public TokenResponse execute(LoginRequest request) {
        User user = getUser(request);
        validatePassword(request.password(), user.getPassword());

        return jwtTokenProvider.createToken(request.email());
    }

    private User getUser(LoginRequest request) {
        String email = request.email();
        String accountId = request.accountId();

        if (email != null) {
            return userRepository.findByEmail(email)
                .orElseThrow(() -> EmailNotFound.EXCEPTION);
        } else if (accountId != null) {
            return userRepository.findByAccountId(accountId)
                .orElseThrow(() -> AccountIdNotFound.EXCEPTION);
        }

        throw LoginIdentifierNotProvided.EXCEPTION;
    }

    private void validatePassword(String requestPassword, String userPassword) {
        if (!passwordEncoder.matches(requestPassword, userPassword)) {
            throw PasswordMisMatch.EXCEPTION;
        }
    }
}
