package com.example.bugle_be.domain.mail.service;

import com.example.bugle_be.domain.auth.exception.EmailNotFound;
import com.example.bugle_be.domain.mail.domain.VerificationCode;
import com.example.bugle_be.domain.mail.domain.VerificationToken;
import com.example.bugle_be.domain.mail.domain.repository.VerificationCodeRepository;
import com.example.bugle_be.domain.mail.domain.repository.VerificationTokenRepository;
import com.example.bugle_be.domain.mail.exception.CodeMisMatch;
import com.example.bugle_be.domain.mail.exception.HashingFailed;
import com.example.bugle_be.domain.mail.exception.TokenMisMatch;
import com.example.bugle_be.domain.mail.presentation.dto.request.SendCodeRequest;
import com.example.bugle_be.domain.mail.presentation.dto.request.VerifyCodeRequest;
import com.example.bugle_be.domain.mail.presentation.dto.response.VerifyTokenResponse;
import com.example.bugle_be.infra.mail.MailSenderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MailService {

    private final VerificationCodeRepository verificationCodeRepository;
    private final VerificationTokenRepository verificationTokenRepository;
    private final MailSenderService mailSenderService;

    private static final SecureRandom random = new SecureRandom();
    private static final String ALGORITHM = "HmacSHA256";
    private static final Long VERIFICATION_TTL = 300L;

    @Value("${spring.mail.security.secret}")
    private String secret;

    @Transactional
    public void sendCode(SendCodeRequest request) {
        String code = createCode();

        verificationCodeRepository.save(
            VerificationCode.builder()
                .email(request.email())
                .code(hash(code))
                .ttl(VERIFICATION_TTL)
                .build()
        );

        mailSenderService.execute(request.email(), code);
    }

    @Transactional
    public VerifyTokenResponse verifyCode(VerifyCodeRequest request) {
        VerificationCode code = verificationCodeRepository.findById(request.email())
            .orElseThrow(() -> EmailNotFound.EXCEPTION);

        if (!code.getCode().equals(hash(request.code()))) {
            throw CodeMisMatch.EXCEPTION;
        }

        verificationCodeRepository.delete(code);

        String token = UUID.randomUUID().toString();

        verificationTokenRepository.save(
            VerificationToken.builder()
                .email(request.email())
                .token(token)
                .ttl(VERIFICATION_TTL)
                .build()
        );

        return new VerifyTokenResponse(token);
    }

    @Transactional
    public void validateToken(String email, String token) {
        VerificationToken verificationToken = verificationTokenRepository.findById(email)
            .orElseThrow(() -> EmailNotFound.EXCEPTION);

        if (!verificationToken.getToken().equals(token)) {
            throw TokenMisMatch.EXCEPTION;
        }

        verificationTokenRepository.delete(verificationToken);
    }

    private String createCode() {
        int code = random.nextInt(1_000_000);
        return String.format("%06d", code);
    }

    private String hash(String code) {
        try {
            Mac mac = Mac.getInstance(ALGORITHM);
            mac.init(new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), ALGORITHM));
            byte[] hash = mac.doFinal(code.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(hash);
        } catch (Exception e) {
            throw HashingFailed.EXCEPTION;
        }
    }
}
