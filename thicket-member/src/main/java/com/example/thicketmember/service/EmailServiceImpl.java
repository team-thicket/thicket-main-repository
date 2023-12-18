package com.example.thicketmember.service;


import com.example.thicketmember.domain.Verification;
import com.example.thicketmember.dto.request.RequestVerificationDto;
import com.example.thicketmember.exception.ExpiredVerificationCodeException;
import com.example.thicketmember.repository.VerificationRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailSendException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService{

    private final JavaMailSender javaMailSender;
    private final SpringTemplateEngine templateEngine;
    private final VerificationRepository codeRepository;

    public String sendMail(String email) {
        Verification verification = Verification.createCode(email);
        String code = verification.getCode();
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        try {
            MimeMessageHelper mimeMessageHelper =
                    new MimeMessageHelper(mimeMessage, false, "UTF-8");

            // 메일 수신자
            mimeMessageHelper.setTo(email);
            // 메일 제목
            mimeMessageHelper.setSubject("이메일 인증을 위한 인증 코드 발송");
            // 메일 내용
            mimeMessageHelper.setText(setContext(code, "email"), true);
            // 메일 본문 내용, HTML 여부
            javaMailSender.send(mimeMessage);

        } catch (MessagingException | MailSendException e) {
            log.info(e.getMessage());
            throw new IllegalArgumentException("메일주소를 확인해주세요.");
        }

        codeRepository.save(verification);

        return code;
    }

    @Override
    public String verifyCode(RequestVerificationDto dto) {

        Verification findVerification = codeRepository.findById(dto.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("인증정보가 없습니다."));

        if (findVerification.getExpirationDate().isBefore(LocalDateTime.now())) {
            throw new ExpiredVerificationCodeException("인증 코드가 만료되었습니다.");
        }

        if(!dto.getCode().equals(findVerification.getCode())) {
            throw new IllegalArgumentException("인증에 실패했습니다.");
        }

        codeRepository.delete(findVerification);
        return "인증에 성공하였습니다.";
    }

    // thymeleaf를 이용해 메일의 html에 인증코드를 삽입
    private String setContext(String code, String type) {
        Context context = new Context();
        context.setVariable("code", code);
        return templateEngine.process(type, context);
    }
}