package com.example.bugle_be.infra.mail.service;

import com.example.bugle_be.domain.mail.exception.MailSendFailed;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailSenderService {

    private final JavaMailSender mailSender;

    public void execute(String email, String code) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("인증 코드");
        message.setText(code);

        try {
            mailSender.send(message);
        } catch (MailException e) {
            throw MailSendFailed.EXCEPTION;
        }
    }
}
