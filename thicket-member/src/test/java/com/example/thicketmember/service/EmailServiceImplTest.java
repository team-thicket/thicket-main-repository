package com.example.thicketmember.service;

import com.example.thicketmember.domain.Verification;
import com.example.thicketmember.dto.request.RequestVerificationDto;
import com.example.thicketmember.exception.ExpiredVerificationCodeException;
import com.example.thicketmember.repository.VerificationRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
class EmailServiceImplTest {
    @Autowired
    EmailService emailService;

    @Autowired
    VerificationRepository codeRepository;

    @Test
    void 인증번호_발송() {
        //given
        String email = "ojs258@naver.com";

        //when
        String sendCode = emailService.sendMail(email);

        //then
        assertThat(codeRepository.findById(email).get().getCode()).isEqualTo(sendCode);

    }

    @Test
    void 인증번호_확인() {
        //given
        String email = "ojs258@naver.com";
        String code = "123456";
        codeRepository.save(new Verification(email,code, LocalDateTime.MAX));
        RequestVerificationDto dto = new RequestVerificationDto();
        dto.setEmail(email);
        dto.setCode(code);

        //when
        String message = emailService.verifyCode(dto);

        //then
        assertThat("인증에 성공하였습니다.").isEqualTo(message);

    }

    @Test
    void 인증번호_불일치() {
        //given
        String email = "ojs258@naver.com";
        String code = "123456";
        codeRepository.save(new Verification(email, code, LocalDateTime.MAX));
        RequestVerificationDto dto = new RequestVerificationDto();
        dto.setEmail(email);
        dto.setCode("wrongCode");

        //when & then
        assertThrows(IllegalArgumentException.class, () -> emailService.verifyCode(dto));
    }

    @Test
    void 시간초과() {
        //given
        String email = "ojs258@naver.com";
        String code = "123456";
        codeRepository.save(new Verification(email,code, LocalDateTime.MIN));
        RequestVerificationDto dto = new RequestVerificationDto();
        dto.setEmail(email);
        dto.setCode(code);

        //when & then
        assertThrows(ExpiredVerificationCodeException.class, () -> emailService.verifyCode(dto));
    }
}