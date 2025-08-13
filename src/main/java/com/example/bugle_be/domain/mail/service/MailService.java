package com.example.bugle_be.domain.mail.service;

import com.example.bugle_be.domain.auth.exception.EmailNotFound;
import com.example.bugle_be.domain.mail.domain.VerificationCode;
import com.example.bugle_be.domain.mail.domain.repository.VerificationCodeRepository;
import com.example.bugle_be.domain.mail.exception.CodeMisMatch;
import com.example.bugle_be.domain.mail.presentation.dto.request.SendCodeRequest;
import com.example.bugle_be.domain.mail.presentation.dto.request.VerifyCodeRequest;
import com.example.bugle_be.global.mail.service.MailSenderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;

@Service
@RequiredArgsConstructor
public class MailService {

    private final VerificationCodeRepository verificationCodeRepository;
    private final MailSenderService mailSenderService;

    private static final SecureRandom random = new SecureRandom();
    private static final Long VERIFICATION_CODE_TTL = 300L;

    @Transactional
    public void sendCode(SendCodeRequest request) {
        String code = createCode();

        verificationCodeRepository.save(
            VerificationCode.builder()
                .email(request.email())
                .code(code)
                .ttl(VERIFICATION_CODE_TTL)
                .build()
        );

        mailSenderService.execute(request.email(), code);
    }

    @Transactional
    public void verifyCode(VerifyCodeRequest request) {
        boolean deleted =
            verificationCodeRepository.deleteByEmailAndCode(request.email(), request.code()) > 0;

        if (!deleted) {
            boolean emailExists = verificationCodeRepository.existsById(request.email());
            throw emailExists ? CodeMisMatch.EXCEPTION : EmailNotFound.EXCEPTION;
        }
    }

    private String createCode() {
        int code = random.nextInt(1_000_000);
        return String.format("%06d", code);
    }
}
