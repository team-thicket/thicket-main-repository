package com.example.thicketauth.controller;

import com.example.thicketauth.dto.RequestMemberSignup;
import com.example.thicketauth.dto.ResponseMemberSignup;
import com.example.thicketauth.exception.EmailDuplicateException;
import com.example.thicketauth.service.MemberSignupService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000/")
@RequestMapping("/members")
public class MemberSignupController {

    private final MemberSignupService memberSignupService;

    @PostMapping("/join")
    public ResponseEntity<ResponseMemberSignup> signup(
            @RequestBody @Valid RequestMemberSignup request) {
        ResponseMemberSignup response = memberSignupService.signup(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @ExceptionHandler(EmailDuplicateException.class)
    public ResponseEntity<String> handleEmailDuplicateException(EmailDuplicateException e) {
        // 적절한 에러 메시지와 함께 400 에러(Bad Request)를 리턴
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
}