package com.example.thicketpayment.service;

import com.example.thicketpayment.domain.Payment;
import com.example.thicketpayment.dto.request.RequestCompletedPaymentDto;
import com.example.thicketpayment.dto.request.RequestExtendingOpenDateDto;
import com.example.thicketpayment.dto.request.RequestCreatePaymentDto;
import com.example.thicketpayment.dto.response.ResponseCompletedPaymentDto;
import com.example.thicketpayment.dto.response.ResponsePaymentForMemberDto;
import com.example.thicketpayment.dto.response.ResponsePaymentForStageDto;
import com.example.thicketpayment.enumerate.HowReceive;
import com.example.thicketpayment.enumerate.Method;
import com.example.thicketpayment.enumerate.State;
import com.example.thicketpayment.repository.PaymentRepository;
import com.sun.jdi.request.DuplicateRequestException;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class PaymentServiceTest {
    @Autowired
    EntityManager em;
    @Autowired
    PaymentService paymentService;
    @Autowired
    PaymentRepository paymentRepository;
    @Test
    void 결제_정보_등록() {
        //given
        RequestCreatePaymentDto dto = new RequestCreatePaymentDto();
        dto.setMemberId("member10");
        dto.setTicketId("ticket20");
        dto.setStageId("stage30");

        //when
        Payment result = paymentService.createPayment(dto);
        em.flush();
        em.clear();
        Payment findPayment = paymentRepository.findByUuid(result.getUuid());

        //then
        assertThat(result.getMemberUuid()).isEqualTo(findPayment.getMemberUuid());
        assertThat(result.getStageUuid()).isEqualTo(findPayment.getStageUuid());
        assertThat(result.getTicketUuid()).isEqualTo(findPayment.getTicketUuid());
    }

    @Test
    void 결제_완료시_결제_정보_갱신() {
        //given
        List<Payment> allPayment = paymentRepository.findAll();

        Payment payment = allPayment.get(0);

        RequestCompletedPaymentDto dto = new RequestCompletedPaymentDto();
        dto.setPaymentId(payment.getUuid());
        dto.setHowReceive(HowReceive.DIRECTLY);
        dto.setState(State.COMPLETED);
        dto.setMethod(Method.KAKAO);
        dto.setStageOpenDate(LocalDate.of(2023,12,2));

        //when
        ResponseCompletedPaymentDto result = paymentService.completedPayment(dto);
        em.flush();
        em.clear();
        Payment findPayment = paymentRepository.findByUuid(dto.getPaymentId());

        //then
        assertThat(result.getHowReceive()).isEqualTo(findPayment.getHowReceive());
        assertThat(result.getState()).isEqualTo(findPayment.getState());
        assertThat(result.getMethod()).isEqualTo(findPayment.getMethod());

        // 년
        assertThat(result.getCancelDeadline().getYear())
                .isEqualTo(findPayment.getCancelDeadline().getYear());

        // 월
        assertThat(result.getCancelDeadline().getMonth())
                .isEqualTo(findPayment.getCancelDeadline().getMonth());

        // 일
        assertThat(result.getCancelDeadline().getDayOfMonth())
                .isEqualTo(findPayment.getCancelDeadline().getDayOfMonth());
    }
    
    @Test
    void 이미_결제된_예매입니다() {
        //given
        List<Payment> allPayment = paymentRepository.findAll();

        Payment payment = allPayment.get(1);

        RequestCompletedPaymentDto dto = new RequestCompletedPaymentDto();
        dto.setPaymentId(payment.getUuid());
        dto.setHowReceive(HowReceive.DIRECTLY);
        dto.setState(State.COMPLETED);
        dto.setMethod(Method.KAKAO);
        dto.setStageOpenDate(LocalDate.of(2023,12,2));

        //when & //then
        assertThrows(DuplicateRequestException.class, () -> paymentService.completedPayment(dto));
    }

    @Test
    void 회원별_전체_결제내역_조회() {
        //given
        String memberId = "member1";

        //when
        List<ResponsePaymentForMemberDto> findDtos =
                paymentService.findAllPaymentByMemberId(memberId);

        //then
        assertThat(findDtos.size()).isEqualTo(4);
    }

    @Test
    void 회원별_결제내역_조회시_결과_없음() {
        //given
        String memberId = "member159";

        //when & then
         assertThrows(NoSuchElementException.class,
                 () -> paymentService.findAllPaymentByMemberId(memberId));
    }

    @Test
    void 무대별_전체_결제내역_조회() {
        //given
        String stageId = "stage1";

        //when

        List<ResponsePaymentForStageDto> findDtos =
                paymentService.findAllPaymentByStageId(stageId);

        //then
        assertThat(findDtos.size()).isEqualTo(5);
    }

    @Test
    void 무대별_결제내역_조회시_결과_없음() {
        //given
        String stageId = "stage348";

        //when & then
        assertThrows(NoSuchElementException.class,
                () -> paymentService.findAllPaymentByStageId(stageId));
    }

    @Test
    void 결제_취소_마감일_연장() {
        //given
        String stageId = "stage30";

        RequestExtendingOpenDateDto dto = new RequestExtendingOpenDateDto();

        dto.setStageId(stageId);
        dto.setStageOpenDate(LocalDate.of(2023,12,2));

        //when
        int result = paymentService.extendingDeadline(dto);

        //then
        assertThat(result).isEqualTo(5);
    }

    @Test
    void 결제_취소_마감일_초과() {
        //given
        List<Payment> allPayment = paymentRepository.findAll();
        String paymentId = allPayment.get(7).getUuid();

        //when & then
        assertThrows(IllegalStateException.class,
                () -> paymentService.cancelPayment(paymentId));
    }

    @Test
    void 결제_취소() {
        //given
        List<Payment> allPayment = paymentRepository.findAll();
        String paymentId = allPayment.get(8).getUuid();

        //when
        Payment payment = paymentService.cancelPayment(paymentId);

        //then
        assertThat(payment.getState()).isEqualTo(State.CANCEL);
    }
}