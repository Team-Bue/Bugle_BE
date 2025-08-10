package com.example.bugle_be.domain.auth.service;

import com.example.bugle_be.domain.auth.domain.RefreshToken;
import com.example.bugle_be.domain.auth.domain.repository.RefreshTokenRepository;
import com.example.bugle_be.domain.auth.exception.RefreshTokenNotFound;
import com.example.bugle_be.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LogoutService {

    private final UserFacade userFacade;
    private final RefreshTokenRepository refreshTokenRepository;

    @Transactional
    public void execute() {
        String email = userFacade.getCurrentUser().getEmail();

        RefreshToken refreshToken = refreshTokenRepository.findById(email)
            .orElseThrow(() -> RefreshTokenNotFound.EXCEPTION);

        refreshTokenRepository.delete(refreshToken);
    }
}
