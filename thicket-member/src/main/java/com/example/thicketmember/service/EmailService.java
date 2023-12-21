package com.example.thicketmember.service;

import com.example.thicketmember.dto.request.RequestEmailDto;
import com.example.thicketmember.dto.request.RequestVerificationDto;

public interface EmailService {
    String sendMail(String email);

    String verifyCode(RequestVerificationDto dto);
}
