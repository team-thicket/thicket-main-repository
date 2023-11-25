package com.example.thicketpayment.domain;

import com.example.thicketpayment.TimeStamp;
import com.example.thicketpayment.enumerate.HowReceive;
import com.example.thicketpayment.enumerate.Method;
import com.example.thicketpayment.enumerate.State;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Payment extends TimeStamp {
    @Id @GeneratedValue
    private Long id;

    @Column
    private LocalDateTime cancelDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private HowReceive howReceive;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Method method;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private State state;

    @Column(nullable = false)
    private String memberUuid;

    @Column(nullable = false)
    private String ticketUuid;

    @Column(nullable = false)
    private String stageUuid;
    // 결제 정보 생성
    public static Payment createPayment(String memberUuid, String ticketUuid, String stageUuid){
        Payment newPayment = new Payment();

        newPayment.cancelDate = null;
        newPayment.howReceive = HowReceive.WAIT;
        newPayment.method = Method.WAIT;
        newPayment.state = State.WAIT;
        newPayment.memberUuid = memberUuid;
        newPayment.ticketUuid = ticketUuid;
        newPayment.stageUuid = stageUuid;

        return newPayment;
    }

    // 티켓 수령 방법 변경
    public void updateHowReceive(HowReceive newHowReceive){
        howReceive = newHowReceive;
    }

    // 결제 진행 상태 변경
    public void updateState(State newState){
        state = newState;
    }

    // 결제 방식 변경
    public void updateMethod(Method newMethod){
        method = newMethod;
    }

    // 취소 가능 일자 변경
    public void updateCancelDate(LocalDateTime newCancelDate) {
        cancelDate = newCancelDate;
    }

    // 결제 취소
    public void cancel(){
        howReceive = HowReceive.CANCEL;
        state = State.CANCEL;
        method = Method.CANCEL;
    }
}
