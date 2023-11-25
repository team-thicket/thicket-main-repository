package com.example.thicketpayment.service;

import com.example.thicketpayment.domain.Payment;

import java.util.List;

public interface PaymentService {
    void createPayment();
    void completedPayment();
    List<Payment> findAllPayment();
    List<Payment> findAllCompletedPayment();
    List<Payment> findAllWaitingPayment();
}
