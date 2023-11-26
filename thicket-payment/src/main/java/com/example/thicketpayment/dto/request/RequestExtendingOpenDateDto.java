package com.example.thicketpayment.dto.request;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class RequestExtendingOpenDateDto {
    @NotNull
    private String stageId;
    @NotNull
    @Future(message = "유효하지않은 날짜입니다.")
    private LocalDate stageOpenDate;
}
