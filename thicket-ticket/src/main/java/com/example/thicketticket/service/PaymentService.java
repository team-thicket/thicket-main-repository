package com.example.thicketticket.service;


import com.example.thicketticket.domain.Payment;
import com.example.thicketticket.dto.request.RequestCompletedPaymentDto;
import com.example.thicketticket.dto.request.RequestCreatePaymentDto;
import com.example.thicketticket.dto.request.RequestExtendingOpenDateDto;
import com.example.thicketticket.dto.response.ResponseCompletedPaymentDto;
import com.example.thicketticket.dto.response.ResponsePaymentForMemberDto;
import com.example.thicketticket.dto.response.ResponsePaymentForStageDto;

import java.util.List;

public interface PaymentService {
    Payment createPayment(RequestCreatePaymentDto dto);
    ResponseCompletedPaymentDto completedPayment(RequestCompletedPaymentDto dto);
    List<ResponsePaymentForMemberDto> findAllPaymentByMemberId(String memberId);
    List<ResponsePaymentForStageDto> findAllPaymentByStageId(String stageId);
    int extendingDeadline(RequestExtendingOpenDateDto dto);
    Payment cancelPayment(String paymentId);
}
