package com.example.thicketpayment.service;

import com.example.thicketpayment.domain.Payment;
import com.example.thicketpayment.dto.request.RequestCompletedPaymentDto;
import com.example.thicketpayment.dto.request.RequestExtendingOpenDateDto;
import com.example.thicketpayment.dto.request.RequestPaymentDto;
import com.example.thicketpayment.dto.response.ResponseCompletedPaymentDto;
import com.example.thicketpayment.dto.response.ResponsePaymentForMemberDto;
import com.example.thicketpayment.dto.response.ResponsePaymentForStageDto;

import java.util.List;

public interface PaymentService {
    Payment createPayment(RequestPaymentDto dto);
    ResponseCompletedPaymentDto completedPayment(RequestCompletedPaymentDto dto);
    List<ResponsePaymentForMemberDto> findAllPaymentByMemberId(String memberId);
    List<ResponsePaymentForStageDto> findAllPaymentByStageId(String stageId);
    int extendingDeadline(RequestExtendingOpenDateDto dto);
    Payment cancelPayment(String paymentId);
}
