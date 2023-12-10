package com.example.thicketauth.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class VerificationCodeService {

    private final RedisTemplate<String, String> redisTemplate;

    public void saveVerificationCode(String email, String code) {
        // 인증 코드 저장
        redisTemplate.opsForValue().set(email, code);
        // 10분 후에 만료
        redisTemplate.expire(email, 10, TimeUnit.MINUTES);
    }

    public String getVerificationCode(String email) {
        // 인증 코드 가져오기
        return redisTemplate.opsForValue().get(email);
    }

    public boolean verifyCode(String email, String code) {
        // 인증 코드 검증
        String savedCode = getVerificationCode(email);
        return savedCode != null && savedCode.equals(code);
    }
}