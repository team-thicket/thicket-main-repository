package com.example.thicketpayment.service;

import com.example.thicketpayment.domain.Payment;
import com.example.thicketpayment.dto.request.RequestCompletedDto;
import com.example.thicketpayment.dto.request.RequestExtendingOpenDateDto;
import com.example.thicketpayment.dto.request.RequestPaymentDto;
import com.example.thicketpayment.dto.response.ResponsePaymentForMemberDto;
import com.example.thicketpayment.dto.response.ResponsePaymentForStageDto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface PaymentService {
    Payment createPayment(RequestPaymentDto dto);
    Payment completedPayment(RequestCompletedDto dto);
    List<ResponsePaymentForMemberDto> findAllPaymentByMemberId(String memberId);
    List<ResponsePaymentForStageDto> findAllPaymentByStageId(String stageId);
    int extendingDeadline(RequestExtendingOpenDateDto dto);
    Payment cancelPayment(String paymentId);
}
