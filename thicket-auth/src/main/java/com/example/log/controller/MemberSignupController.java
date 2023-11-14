package com.example.log.controller;

import com.example.log.dto.MemberSignupRequest;
import com.example.log.dto.MemberSignupResponse;
import com.example.log.service.MemberSignupService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
public class MemberSignupController {

    private final MemberSignupService memberSignupService;

    @PostMapping("/member")
    public ResponseEntity<MemberSignupResponse> signup(
            @RequestBody @Valid MemberSignupRequest request) {
        MemberSignupResponse response = memberSignupService.signup(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}