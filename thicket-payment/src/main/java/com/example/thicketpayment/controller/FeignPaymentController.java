package com.example.thicketpayment.controller;

import com.example.thicketpayment.dto.request.RequestPaymentDto;
import com.example.thicketpayment.service.PaymentService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequiredArgsConstructor
@RequestMapping("feign")
public class FeignPaymentController {
    private final PaymentService paymentService;

    // 예매 완료 -> 결제 대기 상태로 결제 등록
    @PostMapping("payments")
    public ResponseEntity<?> completedTicket(@RequestBody @Valid RequestPaymentDto dto) {
        paymentService.createPayment(dto);
        return ResponseEntity.ok("결제 대기");
    }
    
    // 회원별 전체 결제 목록
    @GetMapping("members")
    public ResponseEntity<?> findAllPayment(@RequestParam @NotEmpty String memberId) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(paymentService.findAllPaymentByMemberId(memberId));
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<?> exceptionHandler(Exception e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> exceptionHandler(BindingResult bindingResult){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(bindingResult.getFieldErrors().get(0).getDefaultMessage());
    }

}
