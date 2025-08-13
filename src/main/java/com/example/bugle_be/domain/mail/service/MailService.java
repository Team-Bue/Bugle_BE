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

@Service
@RequiredArgsConstructor
public class MailService {

    private final VerificationCodeRepository verificationCodeRepository;
    private final MailSenderService mailSenderService;

    private static final Long VERIFICATION_CODE_TTL = 3000L;

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
        String storedCode = verificationCodeRepository.findById(request.email())
            .orElseThrow(() -> EmailNotFound.EXCEPTION)
            .getCode();

        if (!storedCode.equals(request.code())) {
            throw CodeMisMatch.EXCEPTION;
        }

        verificationCodeRepository.deleteById(request.email());
    }

    private String createCode() {
        return String.format("%06d", (int) (Math.random() * 1000000));
    }
}
