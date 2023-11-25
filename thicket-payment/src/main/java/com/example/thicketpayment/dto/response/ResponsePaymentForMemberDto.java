package com.example.thicketpayment.dto.response;

import com.example.thicketpayment.domain.Payment;
import lombok.Data;

@Data
public class ResponsePaymentForMemberDto {
    private String paymentState;
    private String stageId;

    public ResponsePaymentForMemberDto(Payment payment) {
        this.paymentState = payment.getState().toString();
        this.stageId = payment.getStageUuid();
    }
}
