package com.example.thicketauth.controller;

import com.example.thicketauth.dto.RequestChangePasswordDto;
import com.example.thicketauth.dto.RequestDeleteMemberDto;
import com.example.thicketauth.dto.ResponseMemberDto;
import com.example.thicketauth.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    // 회원 정보 조회
    @GetMapping
    public ResponseEntity<ResponseMemberDto> getMemberInfo(Authentication authentication) {
        String email = authentication.getName();
        ResponseMemberDto memberResponse = memberService.getMemberInfo(email);
        return ResponseEntity.ok(memberResponse);
    }

    // 회원 비밀번호 변경
    @PatchMapping
    public ResponseEntity<?> changePassword(@RequestBody RequestChangePasswordDto requestDto, Authentication authentication) {
        String email = authentication.getName();
        memberService.changePassword(email, requestDto.getCurrentPassword(), requestDto.getNewPassword());
        return ResponseEntity.ok("비밀번호가 성공적으로 변경되었습니다.");
    }

    // 회원 탈퇴 (soft delete)
    @DeleteMapping
    public ResponseEntity<?> deleteMember(@RequestBody RequestDeleteMemberDto requestDto, Authentication authentication) {
        String email = authentication.getName();
        memberService.deleteMember(email, requestDto.getPassword());
        return ResponseEntity.ok("회원 탈퇴가 성공적으로 처리되었습니다.");
    }
}