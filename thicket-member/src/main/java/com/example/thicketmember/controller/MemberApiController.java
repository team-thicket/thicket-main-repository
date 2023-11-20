package com.example.thicketmember.controller;

import com.example.thicketmember.dto.request.RequestInactiveDto;
import com.example.thicketmember.dto.request.RequestSetNewPasswordDto;
import com.example.thicketmember.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("members")
@RequiredArgsConstructor
public class MemberApiController {
    private final MemberService memberService;


    @GetMapping("") // api 명세 => GET /members
    public ResponseEntity<?> findMember(HttpServletRequest req){
        return ResponseEntity.ok(memberService.getMemberByToken(req.getHeader("Email")));
    }

    @PatchMapping("") // api 명세 => PATCH /members
    public ResponseEntity<?> changePassword(HttpServletRequest req,
                                            @RequestBody RequestSetNewPasswordDto dto){
        memberService.setNewPassword(req.getHeader("Email"), dto);
        return ResponseEntity.ok("");
    }

    @DeleteMapping("") // api 명세 => DELETE /members
    public ResponseEntity<?> withdraw(HttpServletRequest req,
                                      @RequestBody RequestInactiveDto dto){
        memberService.setInactive(req.getHeader("Email"), dto);
        return ResponseEntity.ok("");
    }

    // @ExceptionHandler를 통해 AOP로 한번에 예외 처리
    @ExceptionHandler
    public ResponseEntity<?> handler(IllegalArgumentException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
}