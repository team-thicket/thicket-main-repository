package com.example.thicketmember.controller;

import com.example.thicketmember.dto.RequestChangeMemberStatusDto;
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
    @GetMapping("")
    public ResponseEntity<?> findMember(){
        return ResponseEntity.ok(memberService.getMemberByToken());
    }

//    @GetMapping("member/")
    // api 명세 => GET members
}
