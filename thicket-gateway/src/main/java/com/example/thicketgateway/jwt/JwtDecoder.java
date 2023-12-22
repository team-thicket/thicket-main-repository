package com.example.thicketgateway.jwt;

import io.jsonwebtoken.Claims;
import reactor.core.publisher.Mono;

public interface JwtDecoder {

    // 추출한 토큰에서 memberId 가져오기
    String tokenToId(String token);

    // 추출한 토큰에서 memberRole 가져오기
    String tokenToRole(String token);

    // 토큰 유효기간 검사
    boolean expiredToken(String token);

    Mono<String> extractMonoRole(String token);

    Mono<String> extractMonoUuid(String token);
}