package com.example.signupemailcertified.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class MailService {
    private final JavaMailSender mailSender;

    public void sendVerificationEmail(String to, String token) {
        String subject = "이메일 인증 안내";
        String content = "인증 링크: http://localhost:8080/api/verify-email?token=" + token;

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setFrom("hmc0501@naver.com");
        message.setSubject(subject);
        message.setText(content);
        mailSender.send(message);
    }

}
