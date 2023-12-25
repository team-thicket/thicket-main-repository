package com.example.thicketmember.controller;


import com.example.thicketmember.dto.request.RequestVerificationDto;
import com.example.thicketmember.exception.ExpiredVerificationCodeException;
import com.example.thicketmember.service.EmailService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/email")
@RequiredArgsConstructor
public class EmailApiController {

    private final EmailService emailService;

    @GetMapping("")
    public ResponseEntity<?> sendJoinMail(@RequestParam("email") String email) {
        emailService.sendMail(email);
        return ResponseEntity.ok().body("인증번호 발송 완료");
    }

    // 인증 코드 검증
    @PostMapping("")
    public ResponseEntity<String> verifyCode(@Valid @RequestBody RequestVerificationDto dto) {
        return ResponseEntity.ok(emailService.verifyCode(dto));
    }

    @ExceptionHandler({IllegalArgumentException.class, ExpiredVerificationCodeException.class})
    public ResponseEntity<String> handler(Exception e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(e.getMessage());
    }
}
