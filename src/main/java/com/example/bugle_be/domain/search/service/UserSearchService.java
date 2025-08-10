package com.example.bugle_be.domain.search.service;

import com.example.bugle_be.domain.search.presentation.dto.request.UserSearchRequest;
import com.example.bugle_be.domain.search.presentation.dto.response.UserSearchResponse;
import com.example.bugle_be.domain.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserSearchService {

    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public UserSearchResponse execute(UserSearchRequest request) {
        List<UserSearchResponse.UserResponse> users = userRepository.getAllByKeyword(request.keyword());

        return new UserSearchResponse(users);
    }
}
