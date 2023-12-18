package com.example.thicketmember.controller;

import com.example.thicketmember.dto.request.*;
import com.example.thicketmember.service.MemberService;
import com.sun.jdi.request.DuplicateRequestException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.webjars.NotFoundException;

@RestController
@RequestMapping("members")
@RequiredArgsConstructor
public class MemberApiController {
    private final MemberService memberService;

    @PostMapping("join")
    public ResponseEntity<?> signup(@RequestBody RequestMemberSignupDto dto) {
        memberService.signup(dto);
        return ResponseEntity.ok("회원가입 성공");
    }

    @PostMapping("{role}") //토큰 반환
    public ResponseEntity<?> signin(@RequestBody RequestMemberSigninDto dto) {
        memberService.signin(dto);
        return ResponseEntity.status(HttpStatus.MOVED_PERMANENTLY).body("로그인 성공");
    }

    @GetMapping("master")
    public ResponseEntity<?> memberList(HttpServletRequest req) {
        if (!req.getHeader("Role").equals("MASTER")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("접근 불가능");
        }
        return ResponseEntity.ok(memberService.findMembers());
    }

    @GetMapping()
    public ResponseEntity<?> memberDetail(HttpServletRequest req) {
        return ResponseEntity.ok(memberService.findMember(req.getHeader("Authorization")));
    }

    @PostMapping("master")
    public ResponseEntity<?> changeMemberRole(HttpServletRequest req, @RequestBody RequestChangeMemberRoleDto dto) {
        if (!req.getHeader("Role").equals("MASTER")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("접근 불가능");
        }
        memberService.changeMemberRole(dto);
        return ResponseEntity.ok("권한 승급 성공");
    }

    @PatchMapping()
    public ResponseEntity<?> changePassword(HttpServletRequest req,
                                            @RequestBody RequestChangePasswordDto dto) {
        memberService.changePassword(req.getHeader("Authorization"),dto);
        return ResponseEntity.ok("비밀번호 변경 성공");
    }

    @DeleteMapping
    public ResponseEntity<?> withdraw(HttpServletRequest req, @RequestBody RequestWithdrawDto dto) {
        memberService.withdraw(req.getHeader("Authorization"),dto);
        return ResponseEntity.ok("회원 탈퇴 성공");
    }
    @PostMapping()
    @ExceptionHandler({IllegalArgumentException.class, DuplicateRequestException.class, NotFoundException.class})
    public ResponseEntity<?> exceptionHandler(Exception e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
}
