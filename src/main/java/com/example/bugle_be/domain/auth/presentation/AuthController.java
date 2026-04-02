package com.example.bugle_be.domain.auth.presentation;

import com.example.bugle_be.domain.auth.presentation.dto.request.LoginRequest;
import com.example.bugle_be.domain.auth.presentation.dto.request.PasswordResetRequest;
import com.example.bugle_be.domain.auth.presentation.dto.request.SignupRequest;
import com.example.bugle_be.domain.auth.presentation.dto.request.TokenRequest;
import com.example.bugle_be.domain.auth.presentation.dto.response.TokenResponse;
import com.example.bugle_be.domain.auth.service.LoginService;
import com.example.bugle_be.domain.auth.service.LogoutService;
import com.example.bugle_be.domain.auth.service.SignupService;
import com.example.bugle_be.domain.auth.service.WithdrawService;
import com.example.bugle_be.domain.auth.service.ReissueService;
import com.example.bugle_be.domain.auth.service.SetDeviceTokenService;
import com.example.bugle_be.domain.auth.service.PasswordResetService;
import com.example.bugle_be.domain.auth.service.DeleteDeviceTokenService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;

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
    private final SetDeviceTokenService setDeviceTokenService;
    private final DeleteDeviceTokenService deleteDeviceTokenService;

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
    public void logout(HttpServletRequest request) {
        logoutService.execute(request);
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

    @PatchMapping("/device-token")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void setToken(@RequestBody @Valid TokenRequest request) {
        setDeviceTokenService.execute(request);
    }

    @DeleteMapping("/device-token")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteToken() {
        deleteDeviceTokenService.execute();
    }
}
