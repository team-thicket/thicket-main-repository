package com.example.thicketmember.service;

import com.example.thicketmember.dto.request.RequestEmailDto;
import com.example.thicketmember.dto.request.RequestVerificationDto;

public interface EmailService {
    void sendMail(RequestEmailDto dto);

    String verifyCode(RequestVerificationDto dto);
}
