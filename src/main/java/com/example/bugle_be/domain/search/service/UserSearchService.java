package com.example.bugle_be.domain.search.service;

import com.example.bugle_be.domain.search.presentation.dto.request.UserSearchRequest;
import com.example.bugle_be.domain.search.presentation.dto.response.UserSearchResponse;
import com.example.bugle_be.domain.user.domain.repository.UserRepository;
import com.example.bugle_be.global.dto.TotalPageCountResponse;
import com.example.bugle_be.global.util.PageUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserSearchService {

    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public UserSearchResponse execute(int page, UserSearchRequest request) {
        List<UserSearchResponse.UserResponse> users = userRepository.findAllByKeyword(page, request.keyword());

        return new UserSearchResponse(users);
    }

    @Transactional(readOnly = true)
    public TotalPageCountResponse executeCount(UserSearchRequest request) {
        int count = PageUtil.getTotalPageCount(
            userRepository.countByKeywordContaining(request.keyword()),
            PageUtil.USER_DEFAULT_PAGE_SIZE
        );

        return new TotalPageCountResponse(count);
    }
}
