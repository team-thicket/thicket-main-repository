package com.example.thicketloader.domain;



import com.example.thicketloader.enumerate.HowReceive;
import com.example.thicketloader.enumerate.Method;
import com.example.thicketloader.enumerate.State;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Payment {
    @Id @GeneratedValue
    private Long id;

    @Column
    private LocalDateTime cancelDeadline;

    private LocalDateTime paymentLimit;

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

    @OneToOne(fetch = FetchType.LAZY)
    private Ticket ticket;
    // 결제 정보 생성
    public static Payment createPayment(String memberUuid, String ticketUuid, String stageUuid){
        Payment newPayment = new Payment();

        newPayment.cancelDeadline = null;
        newPayment.paymentLimit = null;
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
    public void updateCancelDeadline(LocalDateTime newCancelDeadline) {
        cancelDeadline = newCancelDeadline;
    }

    // 결제 취소
    public void cancel(){
        howReceive = HowReceive.CANCEL;
        state = State.CANCEL;
        method = Method.CANCEL;
    }
}
