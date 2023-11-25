package com.example.thicketpayment.dto.request;

import lombok.Data;

import java.time.LocalDate;

@Data
public class RequestExtendingOpenDateDto {
    private String stageId;
    private LocalDate stageOpenDate;
}
