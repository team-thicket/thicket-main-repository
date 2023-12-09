package com.example.thicketticket.dto.response;

import com.example.thicketticket.domain.Payment;
import lombok.Data;

@Data
public class ResponsePaymentForMemberDto {
    private String paymentId;
    private String paymentState;
    private String stageId;

    public ResponsePaymentForMemberDto(Payment payment) {
        paymentId = payment.getUuid();
        paymentState = payment.getState().toString();
        stageId = payment.getStageUuid();
    }
}
