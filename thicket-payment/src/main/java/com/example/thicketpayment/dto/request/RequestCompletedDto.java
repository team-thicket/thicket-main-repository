package com.example.thicketpayment.dto.request;

import com.example.thicketpayment.enumerate.HowReceive;
import com.example.thicketpayment.enumerate.Method;
import com.example.thicketpayment.enumerate.State;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDate;

@Data
public class RequestCompletedDto {
    @NotBlank
    private String paymentId;
    @NotBlank
    private State state;
    @NotBlank
    private Method method;
    @NotBlank
    private HowReceive howReceive;
    @NotBlank
    private LocalDate stageOpenDate;
}
