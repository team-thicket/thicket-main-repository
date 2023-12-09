package com.example.thicketticket.dto.response;

import lombok.Data;

@Data
public class ResponseReadyKakaopayDto {
    private String tid;
    private String next_redirect_pc_url;
}
