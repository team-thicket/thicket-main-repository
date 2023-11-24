package com.example.thicketpayment.domain;

import com.example.thicketpayment.TimeStamp;
import com.example.thicketpayment.enumerate.HowReceive;
import com.example.thicketpayment.enumerate.PaymentMethod;
import com.example.thicketpayment.enumerate.PaymentStatus;
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
    private PaymentMethod paymentMethod;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentStatus paymentStatus;
}
