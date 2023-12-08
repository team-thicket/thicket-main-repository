package com.example.thicketticket.dto.request;

import com.example.thicketticket.enumerate.HowReceive;
import com.example.thicketticket.enumerate.Method;
import com.example.thicketticket.enumerate.State;
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
