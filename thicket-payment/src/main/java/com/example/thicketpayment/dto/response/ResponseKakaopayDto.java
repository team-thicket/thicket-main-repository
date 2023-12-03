package com.example.thicketpayment.dto.response;

import lombok.Data;

@Data
public class ResponseKakaopayDto {
    private String tid;
    private String next_redirect_pc_url;
    private String created_at;
}
