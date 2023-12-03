package com.example.thicketpayment.service;

import com.example.thicketpayment.dto.response.ResponseKakaopayDto;

public interface KakaopayService {
    ResponseKakaopayDto readyKakaopay(String paymentId);
    void approveKakaopay();
}
