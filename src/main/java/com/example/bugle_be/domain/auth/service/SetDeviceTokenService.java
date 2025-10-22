package com.example.bugle_be.domain.auth.service;

import com.example.bugle_be.domain.auth.presentation.dto.request.TokenRequest;
import com.example.bugle_be.domain.user.domain.User;
import com.example.bugle_be.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SetDeviceTokenService {

    private final UserFacade userFacade;

    @Transactional
    public void execute(TokenRequest request) {
        User user = userFacade.getCurrentUser();

        user.setDeviceToken(request.deviceToken());
    }
}
