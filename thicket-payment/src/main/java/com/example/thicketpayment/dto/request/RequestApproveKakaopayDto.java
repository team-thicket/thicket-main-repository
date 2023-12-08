package com.example.thicketpayment.dto.request;

import lombok.Data;

@Data
public class RequestApproveKakaopayDto {
    private String tid;
    private String paymentId;
    private String pgToken;
}
