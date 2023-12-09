package com.example.thicketpayment.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class ResponseReadyKakaopayDto {
    private String tid;
    private String next_redirect_pc_url;
}
