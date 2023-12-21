package com.example.thicketticket.repository;

import com.example.thicketticket.dto.response.ResponsePaymentForMemberDto;
import com.example.thicketticket.dto.response.ResponsePaymentForStageDto;
import com.example.thicketticket.enumerate.State;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class PaymentRepositoryTest {
    @Autowired
    PaymentRepository paymentRepository;
    @Test
    void 공연별_전체_결제_내역_조회() {
        //given
        String stageId = "stage1";

        //when
        List<ResponsePaymentForStageDto> payments = paymentRepository.findAllByStageId(stageId);

        //then
        assertThat(payments.size()).isEqualTo(6);
    }

    @Test
    void 회원별_전체_결제_내역_조회() {
        //given
        String memberId = "member1";

        //when
        List<ResponsePaymentForMemberDto> payments = paymentRepository.findAllByMemberId(memberId);

        //then
        assertThat(payments.size()).isEqualTo(5);
    }

    @Test
    void 공연_날짜_연기시_취소_가능_날짜_벌크_업데이트() {
        //given
        String stageId = "stage30";
        LocalDateTime newCancelDate = LocalDateTime.of(LocalDate.of(2024,11,25), LocalTime.MIDNIGHT);

        //when
        int updateCnt = paymentRepository.updateCancelDateByStageUuid(stageId, newCancelDate, State.COMPLETED);

        //then
        assertThat(updateCnt).isEqualTo(5);
    }

}