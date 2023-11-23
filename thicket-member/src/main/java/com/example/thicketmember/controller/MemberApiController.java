package com.example.thicketmember.controller;

import com.example.thicketmember.dto.request.RequestInactiveDto;
import com.example.thicketmember.dto.request.RequestSetNewPasswordDto;
import com.example.thicketmember.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
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
                                            @RequestBody @Valid RequestSetNewPasswordDto dto){
        memberService.setNewPassword(req.getHeader("Email"), dto);

        return ResponseEntity.ok("비밀번호 변경에 성공하였습니다. 다시 로그인해주세요.");
    }

    @DeleteMapping("") // api 명세 => DELETE /members
    public ResponseEntity<?> withdraw(HttpServletRequest req,
                                      @RequestBody @Valid RequestInactiveDto dto){
        memberService.setInactive(req.getHeader("Email"), dto);
        return ResponseEntity.ok("정상적으로 탈퇴되었습니다.");
    }

    @PatchMapping("/change") // api 명세 => PATCH /members/change
    public ResponseEntity<?> changeAdmin(HttpServletRequest req,
                                         @RequestBody RequestInactiveDto dto){
        memberService.setAdmin(req.getHeader("Email"), dto);
        return ResponseEntity.ok("권한이 변경되었습니다.");
    }

    // @ExceptionHandler를 통해 AOP로 한번에 예외 처리
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> bindingHandler(BindingResult bindingResult) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(bindingResult.getFieldErrors().get(0).getDefaultMessage());
    }
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> exceptionHandler(Exception e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
}
