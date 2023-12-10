package com.example.thicketauth.controller;


import com.example.thicketauth.entity.EmailMessage;
import com.example.thicketauth.dto.RequestEmailDto;
import com.example.thicketauth.dto.RequestVerificationDto;
import com.example.thicketauth.service.EmailService;
import com.example.thicketauth.service.MemberSignupService;
import com.example.thicketauth.service.VerificationCodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000/")
@RequestMapping("/api/email")
@RestController
@RequiredArgsConstructor
public class EmailController {

    private final EmailService emailService;
    private final VerificationCodeService verificationCodeService;

    // 회원가입 이메일 인증 - 요청 시 body로 인증번호 반환하도록 작성하였음
    @PostMapping("")
    public ResponseEntity<?> sendJoinMail(@RequestBody RequestEmailDto requestEmailDto) {
            String code = emailService.sendMail(
            EmailMessage.builder()
            .to(requestEmailDto.getEmail())
            .subject("이메일 인증을 위한 인증 코드 발송")
            .build(),"email");

        verificationCodeService
                .saveVerificationCode(requestEmailDto.getEmail(), code);

        return ResponseEntity.ok().body("성공");
    }

    // 인증 코드 검증
    @PostMapping("/verify")
    public ResponseEntity<String> verifyCode(@RequestBody RequestVerificationDto requestdtoVerification) {
        String code = requestdtoVerification.getCode();
        String email = requestdtoVerification.getEmail();

        if (verificationCodeService.verifyCode(email, code)) {
            return ResponseEntity.ok("인증에 성공하였습니다.");
        } else {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("인증에 실패하였습니다.");
        }
    }

}
