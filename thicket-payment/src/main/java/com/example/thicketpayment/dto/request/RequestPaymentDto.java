package com.example.thicketpayment.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RequestPaymentDto {
    @NotBlank
    private String memberId;
    @NotBlank
    private String stageId;
    @NotBlank
    private String ticketId;
}
