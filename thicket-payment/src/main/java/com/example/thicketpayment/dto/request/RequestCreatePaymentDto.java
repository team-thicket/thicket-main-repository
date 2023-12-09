package com.example.thicketpayment.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RequestCreatePaymentDto {
    @NotNull(message = "회원 아이디 누락")
    private String memberId;
    @NotNull(message = "공연 아이디 누락")
    private String stageId;
    @NotNull(message = "티켓 아이디 누락")
    private String ticketId;
}
