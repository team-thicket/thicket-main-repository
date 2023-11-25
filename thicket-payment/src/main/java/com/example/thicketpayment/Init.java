package com.example.thicketpayment;

import com.example.thicketpayment.domain.Payment;
import com.example.thicketpayment.enumerate.HowReceive;
import com.example.thicketpayment.enumerate.Method;
import com.example.thicketpayment.enumerate.State;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Component
@RequiredArgsConstructor
public class Init {
    private final InitService initService;

    @PostConstruct
    public void init(){
        initService.init();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {
        private final EntityManager em;
        public void init() {
            em.persist(Payment
                    .createPayment("member1","thicket1","stage1"));

            // 이미 결제 완료된 예약 테스트용
            Payment payment2 = Payment
                    .createPayment("member1", "thicket2", "stage2");
            payment2.updateState(State.COMPLETED);
            em.persist(payment2);

            em.persist(Payment
                    .createPayment("member1","thicket3","stage3"));

            em.persist(Payment
                    .createPayment("member1","thicket4","stage4"));

            em.persist(Payment
                    .createPayment("member2","thicket5","stage1"));

            em.persist(Payment
                    .createPayment("member3","thicket6","stage1"));

            em.persist(Payment
                    .createPayment("member4","thicket7","stage1"));

            // 취소 가능 날짜가 지난 결제 내역
            Payment paymentByMember5 = Payment.createPayment("member5", "thicket8", "stage1");
            paymentByMember5.updateState(State.COMPLETED);
            paymentByMember5.updateHowReceive(HowReceive.DIRECTLY);
            paymentByMember5.updateMethod(Method.KAKAO);
            paymentByMember5.updateCancelDeadline(LocalDateTime.now());
            em.persist(paymentByMember5);

            // 취소 가능한 결제 내역
            Payment paymentByMember6 = Payment.createPayment("member6", "thicket9", "stage5");
            paymentByMember6.updateState(State.COMPLETED);
            paymentByMember6.updateHowReceive(HowReceive.DIRECTLY);
            paymentByMember6.updateMethod(Method.KAKAO);
            paymentByMember6.updateCancelDeadline(LocalDateTime.of(LocalDate.of(2024,11,25), LocalTime.MIDNIGHT));
            em.persist(paymentByMember6);
        }
    }
}
