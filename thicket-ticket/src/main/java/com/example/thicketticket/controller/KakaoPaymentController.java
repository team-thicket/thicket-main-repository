package com.example.thicketticket.controller;

import com.example.thicketticket.dto.request.RequestApproveKakaopayDto;
import com.example.thicketticket.service.KakaopayService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("")
public class KakaoPaymentController {
    private final KakaopayService kakaopayService;

    @GetMapping("ready")
    public ResponseEntity<?> ready(@RequestParam(name = "paymentId") String paymentId){
        return ResponseEntity.ok(kakaopayService.readyKakaopay(paymentId));
    }

    @PostMapping("approve")
    public ResponseEntity<?> approve(@RequestBody RequestApproveKakaopayDto dto){
        return ResponseEntity.ok(kakaopayService.approveKakaopay(dto));
    }
}
