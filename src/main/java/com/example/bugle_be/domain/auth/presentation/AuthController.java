package com.example.bugle_be.domain.auth.presentation;

import com.example.bugle_be.domain.auth.presentation.dto.request.LoginRequest;
import com.example.bugle_be.domain.auth.presentation.dto.request.SignupRequest;
import com.example.bugle_be.domain.auth.presentation.dto.response.TokenResponse;
import com.example.bugle_be.domain.auth.service.LoginService;
import com.example.bugle_be.domain.auth.service.LogoutService;
import com.example.bugle_be.domain.auth.service.SignupService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final SignupService signupService;
    private final LoginService loginService;
    private final LogoutService logoutService;

    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public TokenResponse signup(@RequestBody @Valid SignupRequest request) {
        return signupService.execute(request);
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public TokenResponse login(@RequestBody @Valid LoginRequest request) {
        return loginService.execute(request);
    }

    @DeleteMapping("/logout")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void logout() {
        logoutService.execute();
    }
}
