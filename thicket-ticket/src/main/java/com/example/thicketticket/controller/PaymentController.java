package com.example.thicketticket.controller;

import com.example.thicketticket.dto.request.RequestCompletedPaymentDto;
import com.example.thicketticket.service.PaymentService;
import com.sun.jdi.request.DuplicateRequestException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("payments")
public class PaymentController {
    private final PaymentService paymentService;
    
    // 결제 완료 -> 진짜 예매 완료
    @PostMapping()
    public ResponseEntity<?> completedPayment(@RequestBody @Valid RequestCompletedPaymentDto dto) {
        return ResponseEntity.ok(paymentService.completedPayment(dto));
    }

    // 예약 취소 (소프트 딜리트)
    @DeleteMapping()
    public ResponseEntity<?> cancelPayment(@RequestParam @NotEmpty String paymentId) {
        paymentService.cancelPayment(paymentId);
        return ResponseEntity.ok("결제 취소가 완료되었습니다.");
    }

    @ExceptionHandler({DuplicateRequestException.class, IllegalStateException.class, BadRequestException.class})
    public ResponseEntity<?> exceptionHandler(Exception e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> exceptionHandler(BindingResult bindingResult){
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(bindingResult.getFieldErrors().get(0).getDefaultMessage());
    }
}
