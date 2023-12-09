package com.example.thicketticket.controller;

import com.example.thicketticket.dto.request.RequestExtendingOpenDateDto;
import com.example.thicketticket.service.PaymentService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.time.DateTimeException;
import java.util.NoSuchElementException;

@RestController
@RequiredArgsConstructor
@RequestMapping("admin")
public class AdminPaymentController {
    private final PaymentService paymentService;

    // 공연별 전체 결제 목록 -> 추후 집계 함수 적용 예정
    @GetMapping("payments")
    public ResponseEntity<?> findAllPayment(@RequestParam @NotEmpty String stageId) {
        return ResponseEntity.ok(paymentService.findAllPaymentByStageId(stageId));
    }

    // 공연 일자가 뒤로 밀렸을때 취소 가능일자도 뒤로 밈
    @PutMapping("deadline")
    public ResponseEntity<?> updateDeadline(@RequestBody @Valid RequestExtendingOpenDateDto dto){
        return ResponseEntity.ok(paymentService.extendingDeadline(dto)+"개 결제가 수정 되었습니다.");
    }

    @ExceptionHandler({NoSuchElementException.class, DateTimeException.class})
    public ResponseEntity<?> exceptionHandler(Exception e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> exceptionHandler(BindingResult bindingResult){
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(bindingResult.getFieldErrors().get(0).getDefaultMessage());
    }
}
