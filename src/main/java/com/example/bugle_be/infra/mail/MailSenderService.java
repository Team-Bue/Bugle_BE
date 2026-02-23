package com.example.bugle_be.infra.mail;

import com.example.bugle_be.domain.mail.exception.MailSendFailed;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

@Service
@RequiredArgsConstructor
public class MailSenderService {

    private final JavaMailSender mailSender;
    private final SpringTemplateEngine templateEngine;

    @Async
    public void execute(String email, String code) {
        String html = templateEngine.process("verification-template", generateContext(code));

        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setTo(email);
            helper.setSubject("[Bugle] 메일 인증");
            helper.setText(html, true);
            mailSender.send(message);
        } catch (Exception e) {
            throw MailSendFailed.EXCEPTION;
        }
    }

    private Context generateContext(String code) {
        Context context = new Context();
        context.setVariable("code", code);
        return context;
    }
}
