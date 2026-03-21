package com.example.bugle_be.domain.user.presentation;

import com.example.bugle_be.domain.user.presentation.dto.request.UserInfoRequest;
import com.example.bugle_be.domain.user.presentation.dto.response.UserProfileResponse;
import com.example.bugle_be.domain.user.service.QueryMyProfileService;
import com.example.bugle_be.domain.user.service.QueryUserProfileService;
import com.example.bugle_be.domain.user.service.UpdateUserInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final QueryMyProfileService queryMyProfileService;
    private final QueryUserProfileService queryUserProfileService;
    private final UpdateUserInfoService updateUserInfoService;

    @GetMapping("/me")
    public UserProfileResponse getMyProfile() {
        return queryMyProfileService.execute();
    }

    @GetMapping("/{user-id}")
    public UserProfileResponse getUserProfile(@PathVariable(name = "user-id") Long userId) {
        return queryUserProfileService.execute(userId);
    }

    @PatchMapping("/me")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateUserInfo(@RequestBody @Valid UserInfoRequest request) {
        updateUserInfoService.execute(request);
    }
}
