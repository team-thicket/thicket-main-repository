package com.example.thicketpayment.dto.response;

import com.example.thicketpayment.domain.Payment;
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
