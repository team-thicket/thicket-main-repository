package com.example.thicketauth.smtp.controller;


import com.example.thicketauth.smtp.domain.EmailMessage;
import com.example.thicketauth.smtp.dto.EmailPostDto;
import com.example.thicketauth.smtp.dto.VerificationRequestDto;
import com.example.thicketauth.smtp.service.EmailServiceImpl;
import com.example.thicketauth.smtp.service.VerificationCodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/email")
@RestController
@RequiredArgsConstructor
public class EmailController {

    private final EmailServiceImpl emailServiceImpl;
    private final VerificationCodeService verificationCodeService;


    // 회원가입 이메일 인증 - 요청 시 body로 인증번호 반환하도록 작성하였음
    @PostMapping("")
    public ResponseEntity<?> sendJoinMail(@RequestBody EmailPostDto emailPostDto) {
        String code = emailServiceImpl.sendMail(
                EmailMessage.builder()
                        .to(emailPostDto.getEmail())
                        .subject("이메일 인증을 위한 인증 코드 발송")
                        .build(),
                "email");

        verificationCodeService.saveVerificationCode(emailPostDto.getEmail(), code);

//        EmailResponseDto emailResponseDto = new EmailResponseDto();
//        emailResponseDto.setCode(code);
//        System.out.println(code);
        return ResponseEntity.ok().body("성공");
    }

    // 인증 코드 검증
    @PostMapping("/verify")
    public ResponseEntity<String> verifyCode(@RequestBody VerificationRequestDto verificationRequestdto) {
      String code = verificationRequestdto.getCode();
        String email = verificationRequestdto.getEmail();

        if (verificationCodeService.verifyCode(email, code)) {
            return ResponseEntity.ok("인증에 성공하였습니다.");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("인증에 실패하였습니다.");
        }
    }

}
