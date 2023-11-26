package com.example.thicketpayment.dto.request;

import com.example.thicketpayment.enumerate.HowReceive;
import com.example.thicketpayment.enumerate.Method;
import com.example.thicketpayment.enumerate.State;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class RequestCompletedPaymentDto {
    @NotNull
    private String paymentId;
    @NotNull
    private State state;
    @NotNull
    private Method method;
    @NotNull
    private HowReceive howReceive;
    @Future @NotNull
    private LocalDate stageOpenDate;
}
