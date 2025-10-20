package com.example.bugle_be.domain.follow.domain.repository;

import com.example.bugle_be.domain.follow.presentation.dto.response.FollowResponse;

import java.util.List;

public interface FollowRepositoryCustom {

    List<FollowResponse.UserDto> findAllFollowersByUserId(Long userId);

    Long countFollowersByUserId(Long userId);

    List<FollowResponse.UserDto> findAllFollowingsByUserId(Long userId);

    Long countFollowingsByUserId(Long userId);
}
