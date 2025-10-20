package com.example.bugle_be.domain.follow.service;

import com.example.bugle_be.domain.follow.domain.repository.FollowRepository;
import com.example.bugle_be.domain.follow.presentation.dto.response.FollowResponse;
import com.example.bugle_be.domain.user.domain.User;
import com.example.bugle_be.domain.user.facade.UserFacade;
import com.example.bugle_be.global.dto.TotalPageCountResponse;
import com.example.bugle_be.global.util.PageUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QueryFollowersService {

    private final UserFacade userFacade;
    private final FollowRepository followRepository;

    @Transactional(readOnly = true)
    public FollowResponse execute(int page) {
        User user = userFacade.getCurrentUser();
        List<FollowResponse.UserDto> followers = followRepository.findAllFollowersByUserId(page, user.getId());

        return new FollowResponse(followers);
    }

    @Transactional(readOnly = true)
    public TotalPageCountResponse executeCount() {
        User user = userFacade.getCurrentUser();

        int count = PageUtil.getTotalPageCount(
            followRepository.countFollowersByUserId(user.getId()),
            PageUtil.USER_DEFAULT_PAGE_SIZE
        );

        return new TotalPageCountResponse(count);
    }
}
