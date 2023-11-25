package com.example.thicketpayment.service;

import com.example.thicketpayment.domain.Payment;
import com.example.thicketpayment.dto.request.RequestCompletedDto;
import com.example.thicketpayment.dto.request.RequestExtendingOpenDateDto;
import com.example.thicketpayment.dto.request.RequestPaymentDto;
import com.example.thicketpayment.dto.response.ResponsePaymentForMemberDto;
import com.example.thicketpayment.dto.response.ResponsePaymentForStageDto;
import com.example.thicketpayment.enumerate.State;
import com.example.thicketpayment.repository.PaymentRepository;
import com.sun.jdi.request.DuplicateRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService{
    private final PaymentRepository paymentRepository;
    @Override
    @Transactional
    public Payment createPayment(RequestPaymentDto dto) {
        // 멤버, 티켓, 공연 순서
        return paymentRepository.save(Payment
                .createPayment(dto.getMemberId(), dto.getTicketId(), dto.getStageId()));
    }

    @Override
    @Transactional
    public Payment completedPayment(RequestCompletedDto dto) {
        Payment findPayment = paymentRepository.findByUuid(dto.getPaymentId());

        if (!(findPayment.getState().equals(State.WAIT))) {
            throw new DuplicateRequestException("이미 결제된 예매입니다.");
        }
        findPayment.updateHowReceive(dto.getHowReceive());
        findPayment.updateState(dto.getState());
        findPayment.updateMethod(dto.getMethod());
        // 공연 시작 2일전 자정까지 취소가능
        LocalDate cancelDeadline = dto.getStageOpenDate().minusDays(2);
        findPayment.updateCancelDeadline(cancelDeadline.atTime(LocalTime.MIDNIGHT));

        return findPayment;
    }

    // paging 해야함
    @Override
    public List<ResponsePaymentForMemberDto> findAllPaymentByMemberId(String memberId) {
        List<ResponsePaymentForMemberDto> payments = paymentRepository.findAllByMemberId(memberId);
        if (payments.isEmpty()) {
            throw new NoSuchElementException("조회된 결제 정보가 없습니다.");
        }
        return payments;
    }

    @Override
    public List<ResponsePaymentForStageDto> findAllPaymentByStageId(String stageId) {
        List<ResponsePaymentForStageDto> payments = paymentRepository.findAllByStageId(stageId);

        if (payments.isEmpty()) {
            throw new NoSuchElementException("조회된 결제 정보가 없습니다.");
        }
        return payments;
    }

    @Override
    public int extendingDeadline(RequestExtendingOpenDateDto dto) {
        LocalDate cancelDeadline = dto.getStageOpenDate().minusDays(2);
        return paymentRepository
                .updateCancelDateByStageUuid(
                dto.getStageId(),
                cancelDeadline.atTime(LocalTime.MIDNIGHT));

    }

    @Override
    @Transactional
    public Payment cancelPayment(String paymentId) {
        Payment findPayment = paymentRepository.findByUuid(paymentId);
        // 취소가능 날짜가 지났으면
        if(findPayment.getCancelDeadline().isBefore(LocalDateTime.now())) {
            throw new IllegalStateException("취소 불가능한 예매 입니다.");
        }
        findPayment.cancel();

        return findPayment;
    }
}
