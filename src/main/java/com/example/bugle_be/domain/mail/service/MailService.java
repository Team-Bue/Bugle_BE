package com.example.bugle_be.domain.mail.service;

import com.example.bugle_be.domain.auth.exception.EmailNotFound;
import com.example.bugle_be.domain.mail.domain.VerificationCode;
import com.example.bugle_be.domain.mail.domain.repository.VerificationCodeRepository;
import com.example.bugle_be.domain.mail.exception.CodeMisMatch;
import com.example.bugle_be.domain.mail.exception.HashingFailed;
import com.example.bugle_be.domain.mail.presentation.dto.request.SendCodeRequest;
import com.example.bugle_be.domain.mail.presentation.dto.request.VerifyCodeRequest;
import com.example.bugle_be.infra.mail.MailSenderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;
import java.util.Base64;

@Service
@RequiredArgsConstructor
public class MailService {

    private final VerificationCodeRepository verificationCodeRepository;
    private final MailSenderService mailSenderService;

    private static final SecureRandom random = new SecureRandom();
    private static final String ALGORITHM = "HmacSHA256";
    private static final Long VERIFICATION_CODE_TTL = 300L;

    @Value("${spring.mail.security.secret}")
    private String secret;

    @Transactional
    public void sendCode(SendCodeRequest request) {
        String code = createCode();

        verificationCodeRepository.save(
            VerificationCode.builder()
                .email(request.email())
                .code(hash(code))
                .ttl(VERIFICATION_CODE_TTL)
                .build()
        );

        mailSenderService.execute(request.email(), code);
    }

    @Transactional
    public void verifyCode(VerifyCodeRequest request) {
        boolean deleted =
            verificationCodeRepository.deleteByEmailAndCode(request.email(), hash(request.code())) > 0;

        if (!deleted) {
            boolean emailExists = verificationCodeRepository.existsById(request.email());
            throw emailExists ? CodeMisMatch.EXCEPTION : EmailNotFound.EXCEPTION;
        }
    }

    private String createCode() {
        int code = random.nextInt(1_000_000);
        return String.format("%06d", code);
    }

    private String hash(String code) {
        try {
            Mac mac = Mac.getInstance(ALGORITHM);
            mac.init(new SecretKeySpec(secret.getBytes(), ALGORITHM));
            byte[] hash = mac.doFinal(code.getBytes());
            return Base64.getEncoder().encodeToString(hash);
        } catch (Exception e) {
            throw HashingFailed.EXCEPTION;
        }
    }
}
