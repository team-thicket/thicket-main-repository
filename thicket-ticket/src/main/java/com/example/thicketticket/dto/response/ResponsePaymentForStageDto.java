package com.example.thicketticket.dto.response;

import com.example.thicketticket.domain.Payment;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ResponsePaymentForStageDto {
    private String paymentId;
    private String state;
    private String method;
    private String howReceive;
    private LocalDateTime cancelDate;

    public ResponsePaymentForStageDto(Payment payment) {
        this.paymentId = payment.getUuid();
        this.state = payment.getState().toString();
        this.method = payment.getMethod().toString();
        this.howReceive = payment.getHowReceive().toString();
        this.cancelDate = payment.getCancelDeadline();
    }
}
