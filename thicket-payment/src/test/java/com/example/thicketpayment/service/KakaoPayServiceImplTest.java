package com.example.thicketpayment.service;

import com.example.thicketpayment.dto.request.RequestApproveKakaopayDto;
import com.example.thicketpayment.dto.response.ResponseCompletedKakaopayDto;
import com.example.thicketpayment.dto.response.ResponseReadyKakaopayDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

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
        ResponseReadyKakaopayDto result = kakaopayService.readyKakaopay(paymentId);

        //then
        assertThat(result.getTid()).isNotNull();
        assertThat(result.getNext_redirect_pc_url()).isNotNull();

        System.out.println(result.getTid());
        System.out.println(result.getNext_redirect_pc_url());
    }

// 능력 부족일지는 모르겠지만 이걸 어떻게 병렬 테스트 코드를 짜야할지 도저히 모르겠다.
// 결제 대기 토큰값을 얻어오는 방법을 모르겠다.
// 일단 따로 돌리면서 토큰값을 직접 대입해주면 아래 테스트 코드는 성공함.
//    @Test
//    void 카카오_페이_결제_승인() throws InterruptedException {
//        //given
//
//        String paymentId = paymentService
//                .findAllPaymentByMemberId("member1").get(0).getPaymentId();
//        String token= "8dd6daf99c115ccbbbd0";
//        String tid = "T56d44d61f934588afe5";
//
//        RequestApproveKakaopayDto approveDto = new RequestApproveKakaopayDto();
//        approveDto.setPgToken(token);
//        approveDto.setPaymentId(paymentId);
//        approveDto.setTid(tid);
//
//        //when
//        ResponseCompletedKakaopayDto completedDto = kakaopayService.approveKakaopay(approveDto);
//
//        //then
//        assertThat(completedDto.getItem_name()).isEqualTo("thicket1");
//        assertThat(completedDto.getTid()).isEqualTo(tid);
//    }
}