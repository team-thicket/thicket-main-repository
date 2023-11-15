package com.example.thicketmember.controller;

import com.example.thicketmember.dto.request.RequestInactiveDto;
import com.example.thicketmember.dto.request.RequestSetNewPasswordDto;
import com.example.thicketmember.service.MemberService;
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
    public ResponseEntity<?> findMember(){
        return ResponseEntity.ok(memberService.getMemberByToken());
    }

    @PatchMapping("") // api 명세 => PATCH /members
    public ResponseEntity<?> changePassword(@RequestBody RequestSetNewPasswordDto dto){
        memberService.setNewPassword(dto);
        return ResponseEntity.ok("");
    }

    @DeleteMapping("") // api 명세 => DELETE /members
    public ResponseEntity<?> withdraw(@RequestBody RequestInactiveDto dto){
        memberService.setInactive(dto);
        return ResponseEntity.ok("");
    }

    @ExceptionHandler
    public ResponseEntity<?> handler(IllegalArgumentException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
}
