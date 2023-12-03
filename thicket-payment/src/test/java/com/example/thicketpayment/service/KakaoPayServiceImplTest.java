package com.example.thicketpayment.service;

import com.example.thicketpayment.dto.response.ResponseKakaopayDto;
import com.example.thicketpayment.dto.response.ResponsePaymentForMemberDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class KakaoPayServiceImplTest {
    @Autowired
    KakaopayService kakaopayService;

    @Autowired
    PaymentService paymentService;

    @Test
    void 카카오페이_결제_준비() {
        //given
        String paymentId = paymentService
                .findAllPaymentByMemberId("member1").get(0).getPaymentId();

        //when
        ResponseKakaopayDto result = kakaopayService.readyKakaopay(paymentId);

        //then
        assertThat(result.getTid()).isNotNull();
        assertThat(result.getNext_redirect_pc_url()).isNotNull();
        assertThat(result.getCreated_at()).isNotNull();
    }

    @Test
    void approveKakaopay() {
    }
}