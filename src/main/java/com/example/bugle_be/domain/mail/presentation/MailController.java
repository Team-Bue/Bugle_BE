package com.example.bugle_be.domain.mail.presentation;

import com.example.bugle_be.domain.mail.presentation.dto.request.SendCodeRequest;
import com.example.bugle_be.domain.mail.presentation.dto.request.VerifyCodeRequest;
import com.example.bugle_be.domain.mail.service.MailService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mail")
@RequiredArgsConstructor
public class MailController {

    private final MailService mailService;

    @PostMapping("/send")
    @ResponseStatus(HttpStatus.OK)
    public void sendCode(@RequestBody @Valid SendCodeRequest request) {
        mailService.sendCode(request);
    }

    @PostMapping("/verify")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void verifyCode(@RequestBody @Valid VerifyCodeRequest request) {
        mailService.verifyCode(request);
    }
}
