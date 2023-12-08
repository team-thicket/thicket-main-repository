package com.example.thicketpayment.controller;

import com.example.thicketpayment.dto.request.RequestApproveKakaopayDto;
import com.example.thicketpayment.service.KakaopayService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping()
public class KakaoPaymentController {
    private final KakaopayService kakaopayService;

    @GetMapping("ready")
    public ResponseEntity<?> ready(@RequestParam String paymentId){
        return ResponseEntity.ok(kakaopayService.readyKakaopay(paymentId));
    }

    @GetMapping("approve")
    public ResponseEntity<?> approve(@RequestBody RequestApproveKakaopayDto dto){
        return ResponseEntity.ok(kakaopayService.approveKakaopay(dto));
    }
}
