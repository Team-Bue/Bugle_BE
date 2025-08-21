package com.example.bugle_be.domain.auth.presentation;

import com.example.bugle_be.domain.auth.presentation.dto.request.LoginRequest;
import com.example.bugle_be.domain.auth.presentation.dto.request.PasswordResetRequest;
import com.example.bugle_be.domain.auth.presentation.dto.request.SignupRequest;
import com.example.bugle_be.domain.auth.presentation.dto.response.TokenResponse;
import com.example.bugle_be.domain.auth.service.*;
import jakarta.servlet.http.HttpServletRequest;
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
    private final WithdrawService withdrawService;
    private final ReissueService reissueService;
    private final PasswordResetService passwordResetService;

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

    @DeleteMapping("/withdraw")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void withdraw() {
        withdrawService.execute();
    }

    @PostMapping("/reissue")
    @ResponseStatus(HttpStatus.OK)
    public TokenResponse reissue(HttpServletRequest request) {
        return reissueService.execute(request);
    }

    @PatchMapping("/password")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void passwordReset(@RequestBody @Valid PasswordResetRequest request) {
        passwordResetService.execute(request);
    }
}
