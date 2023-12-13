package com.example.thicketmember.service;


import com.example.thicketmember.dto.request.RequestEmailDto;
import com.example.thicketmember.dto.request.RequestVerificationDto;
import com.example.thicketmember.repository.VerificationCodeRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.util.Random;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService{

    private final JavaMailSender javaMailSender;
    private final SpringTemplateEngine templateEngine;
    private final VerificationCodeRepository codeRepository;

    public void sendMail(RequestEmailDto dto) {
        String verificationCode = createCode();
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        try {
            MimeMessageHelper mimeMessageHelper =
                    new MimeMessageHelper(mimeMessage, false, "UTF-8");

            // 메일 수신자
            mimeMessageHelper.setTo(dto.getEmail());
            // 메일 제목
            mimeMessageHelper.setSubject("이메일 인증을 위한 인증 코드 발송");
            // 메일 내용
            mimeMessageHelper.setText(setContext(verificationCode, "email"), true);
            // 메일 본문 내용, HTML 여부
            javaMailSender.send(mimeMessage);
            log.info("Code Send Success");

        } catch (MessagingException e) {
            log.info("Code Send Fail");
            throw new RuntimeException("인증메일 전송에 실패하였습니다.");
        }

        codeRepository.save(dto.getEmail(), verificationCode);
    }

    @Override
    public String verifyCode(RequestVerificationDto dto) {
        String email = dto.getEmail();

        if(!dto.getCode().equals(codeRepository.find(email))) {
            throw new IllegalArgumentException("인증에 실패하였습니다.");
        }

        codeRepository.delete(email);
        return "인증에 성공하였습니다.";
    }

    // 인증번호 생성 메서드
    private String createCode() {
        Random random = new Random();
        StringBuilder key = new StringBuilder();

        for (int i = 0; i < 8; i++) {
            int index = random.nextInt(4);
            switch (index) {
                case 0: key.append((char) (random.nextInt(26) + 97)); break;
                case 1: key.append((char) (random.nextInt(26) + 65)); break;
                default: key.append(random.nextInt(9));
            }
        }
        return key.toString();
    }

    // thymeleaf를 이용해 메일의 html에 인증코드를 삽입
    private String setContext(String code, String type) {
        Context context = new Context();
        context.setVariable("code", code);
        return templateEngine.process(type, context);
    }
}