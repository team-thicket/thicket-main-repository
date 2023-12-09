package com.example.thicketpayment.service;

import com.example.thicketpayment.dto.request.RequestApproveKakaopayDto;
import com.example.thicketpayment.dto.response.ResponseCompletedKakaopayDto;
import com.example.thicketpayment.dto.response.ResponseReadyKakaopayDto;

public interface KakaopayService {
    ResponseReadyKakaopayDto readyKakaopay(String paymentId);
    ResponseCompletedKakaopayDto approveKakaopay(RequestApproveKakaopayDto dto);
}
