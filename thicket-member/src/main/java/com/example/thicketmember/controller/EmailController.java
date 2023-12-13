package com.example.thicketmember.controller;


import com.example.thicketmember.dto.request.RequestEmailDto;
import com.example.thicketmember.dto.request.RequestVerificationDto;
import com.example.thicketmember.service.EmailService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/email")
@RestController
@RequiredArgsConstructor
public class EmailController {

    private final EmailService emailService;


    // 회원가입 이메일 인증 - 요청 시 body로 인증번호 반환하도록 작성하였음
    @PostMapping("")
    public ResponseEntity<?> sendJoinMail(@RequestBody RequestEmailDto requestEmailDto) {
        emailService.sendMail(requestEmailDto);
        return ResponseEntity.ok().body("성공");
    }

    // 인증 코드 검증
    @PostMapping("/verify")
    public ResponseEntity<String> verifyCode(@RequestBody RequestVerificationDto dto) {
        return ResponseEntity.ok(emailService.verifyCode(dto));
    }

    @ExceptionHandler({MessagingException.class, IllegalArgumentException.class})
    public ResponseEntity<String> handler(Exception e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(e.getCause().getMessage());
    }
}
