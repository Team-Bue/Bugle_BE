package com.example.bugle_be.domain.auth.service;

import com.example.bugle_be.domain.auth.domain.RefreshToken;
import com.example.bugle_be.domain.auth.domain.repository.RefreshTokenRepository;
import com.example.bugle_be.domain.auth.exception.InvalidRefreshToken;
import com.example.bugle_be.domain.auth.exception.RefreshTokenNotFound;
import com.example.bugle_be.domain.auth.presentation.dto.response.TokenResponse;
import com.example.bugle_be.global.security.jwt.JwtProperties;
import com.example.bugle_be.global.security.jwt.JwtTokenProvider;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReissueService {

    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final JwtProperties jwtProperties;

    @Transactional
    public TokenResponse execute(HttpServletRequest request) {
        String refreshToken = parseAndValidate(request.getHeader(jwtProperties.header()));
        String email = getEmailAndDeleteRefreshToken(refreshToken);

        return jwtTokenProvider.createToken(email);
    }

    private String parseAndValidate(String token) {
        if (token == null) throw InvalidRefreshToken.EXCEPTION;

        String parseToken = jwtTokenProvider.parseToken(token);
        if (parseToken == null || !jwtTokenProvider.validateRefreshToken(parseToken)) {
            throw InvalidRefreshToken.EXCEPTION;
        }

        return parseToken;
    }

    private String getEmailAndDeleteRefreshToken(String refreshToken) {
        RefreshToken redisRefreshToken = refreshTokenRepository.findByRefreshToken(refreshToken)
            .orElseThrow(() -> RefreshTokenNotFound.EXCEPTION);

        String email = redisRefreshToken.getEmail();
        refreshTokenRepository.delete(redisRefreshToken);

        return email;
    }
}
