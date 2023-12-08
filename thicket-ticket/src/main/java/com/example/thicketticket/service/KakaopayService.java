package com.example.thicketticket.service;


import com.example.thicketticket.dto.request.RequestApproveKakaopayDto;
import com.example.thicketticket.dto.response.ResponseCompletedKakaopayDto;
import com.example.thicketticket.dto.response.ResponseReadyKakaopayDto;

public interface KakaopayService {
    ResponseReadyKakaopayDto readyKakaopay(String paymentId);
    ResponseCompletedKakaopayDto approveKakaopay(RequestApproveKakaopayDto dto);
}
