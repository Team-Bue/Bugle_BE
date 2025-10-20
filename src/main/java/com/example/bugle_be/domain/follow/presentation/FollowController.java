package com.example.bugle_be.domain.follow.presentation;

import com.example.bugle_be.domain.follow.presentation.dto.response.FollowResponse;
import com.example.bugle_be.domain.follow.service.FollowService;
import com.example.bugle_be.domain.follow.service.QueryFollowersService;
import com.example.bugle_be.domain.follow.service.QueryFollowingsService;
import com.example.bugle_be.domain.follow.service.UnFollowService;
import com.example.bugle_be.global.dto.TotalPageCountResponse;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Validated
@RestController
@RequestMapping("/follows")
@RequiredArgsConstructor
public class FollowController {

    private final FollowService followService;
    private final UnFollowService unFollowService;
    private final QueryFollowersService queryFollowersService;
    private final QueryFollowingsService queryFollowingsService;

    @PostMapping("/{following-id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void follow(@PathVariable("following-id") Long followingId) {
        followService.execute(followingId);
    }

    @DeleteMapping("/{following-id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void unfollow(@PathVariable("following-id") Long followingId) {
        unFollowService.execute(followingId);
    }

    @GetMapping("/followers")
    @ResponseStatus(HttpStatus.OK)
    public FollowResponse getFollowers(
        @RequestParam(value = "page", required = false, defaultValue = "1") @Positive Integer page
    ) {
        return queryFollowersService.execute();
    }

    @GetMapping("/followers/count")
    @ResponseStatus(HttpStatus.OK)
    public TotalPageCountResponse getFollowersCount() {
        return queryFollowersService.executeCount();
    }

    @GetMapping("/followings")
    @ResponseStatus(HttpStatus.OK)
    public FollowResponse getFollowing(
        @RequestParam(value = "page", required = false, defaultValue = "1") @Positive Integer page
    ) {
        return queryFollowingsService.execute();
    }

    @GetMapping("/followings/count")
    @ResponseStatus(HttpStatus.OK)
    public TotalPageCountResponse getFollowingCount() {
        return queryFollowingsService.executeCount();
    }
}
