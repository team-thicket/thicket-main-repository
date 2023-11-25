package com.example.thicketpayment.dto.response;

import com.example.thicketpayment.domain.Payment;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class ResponseStateDto {
    private String paymentState;
    private String stageId;

    public ResponseStateDto(Payment payment) {
        this.paymentState = payment.getState().toString();
        this.stageId = payment.getStageUuid();
    }
}
