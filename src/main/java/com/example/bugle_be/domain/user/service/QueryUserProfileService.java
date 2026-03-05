package com.example.bugle_be.domain.user.service;

import com.example.bugle_be.domain.user.domain.User;
import com.example.bugle_be.domain.user.domain.repository.UserRepository;
import com.example.bugle_be.domain.user.exception.UserNotFound;
import com.example.bugle_be.domain.user.presentation.dto.response.UserProfileResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class QueryUserProfileService {

    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public UserProfileResponse execute(Long userId) {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> UserNotFound.EXCEPTION);

        return UserProfileResponse.from(user);
    }
}
