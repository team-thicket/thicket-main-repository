package com.example.thicketticket.dto.response;

import com.example.thicketticket.domain.Payment;
import com.example.thicketticket.enumerate.HowReceive;
import com.example.thicketticket.enumerate.Method;
import com.example.thicketticket.enumerate.State;
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
