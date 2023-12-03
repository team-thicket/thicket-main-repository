package com.example.thicketpayment.controller;

import com.example.thicketpayment.service.KakaopayService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("kakao")
public class KakaoPaymentController {
    private final KakaopayService kakaopayService;

    @GetMapping("")
    public ResponseEntity<?> ready(@RequestParam String paymentId){
        return ResponseEntity.ok(kakaopayService.readyKakaopay(paymentId));
    }
}
