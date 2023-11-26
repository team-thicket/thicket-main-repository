package com.example.thicketpayment.dto.response;

import com.example.thicketpayment.domain.Payment;
import com.example.thicketpayment.enumerate.HowReceive;
import com.example.thicketpayment.enumerate.Method;
import com.example.thicketpayment.enumerate.State;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ResponseCompletedPaymentDto {
    private String paymentId;
    private String ticketId;
    private State state;
    private Method method;
    private HowReceive howReceive;
    private LocalDateTime cancelDeadline;

    public static ResponseCompletedPaymentDto toDto(Payment payment){
        ResponseCompletedPaymentDto dto = new ResponseCompletedPaymentDto();

        dto.paymentId = payment.getUuid();
        dto.ticketId = payment.getTicketUuid();
        dto.state = payment.getState();
        dto.method = payment.getMethod();
        dto.howReceive = payment.getHowReceive();
        dto.cancelDeadline = payment.getCancelDeadline();

        return dto;
    }
}
