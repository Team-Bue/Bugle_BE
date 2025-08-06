package com.example.bugle_be.domain.user.domain.repository;

import com.example.bugle_be.domain.search.presentation.dto.response.UserSearchResponse;

import java.util.List;

public interface UserRepositoryCustom {

    List<UserSearchResponse.UserResponse> getAllByKeyword(String keyword);
}
