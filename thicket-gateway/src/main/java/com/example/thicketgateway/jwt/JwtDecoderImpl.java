package com.example.thicketgateway.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import reactor.core.publisher.Mono;

import java.security.Key;

@Component
@Slf4j
@PropertySource("classpath:secretKey.properties")
public class JwtDecoderImpl implements JwtDecoder{


    // secretKey값을 디코딩한 결과를 저장하는 Key
    private Key key;



    // JWT payload에 들어갈 유효기간 (계산용)
    private final long tokenValidMillisecond = 1000L * 60 * 60;

    // 빈이 주입받은 직후 자동 실행되는 메소드, secretKey 값을 Base64로 디코딩
    public JwtDecoderImpl(@Value("${jwtSecret}") String secret) {
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    @Override
    public boolean expiredToken(String token){
        // 토큰 유효성 검증 수행
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (Exception e){
            log.info("Token 검증 실패");
        }
        return false;
    }

    @Override
    public Mono<String> extractMonoRole(String token) {
        // 토큰을 들고와서 Mono<role>로 반환해주는 메소드
        return Mono.just(Jwts.parserBuilder().setSigningKey(key).build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject());
    }

    @Override
    public Mono<String> extractMonoUuid(String token) {
        // 토큰을 들고와서 Mono<id>로 반환해주는 메소드
        return Mono.just(Jwts.parserBuilder().setSigningKey(key).build()
                .parseClaimsJws(token)
                .getBody()
                .getAudience());
    }

    @Override
    public String tokenToId(String token) {
        // 토큰을 들고와서 id로 반환해주는 메소드
        return Jwts.parserBuilder().setSigningKey(key).build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    @Override
    public String tokenToRole(String token) {
        // 토큰을 들고와서 role을 반환해주는 메소드
        return Jwts.parserBuilder().setSigningKey(key).build()
                .parseClaimsJws(token)
                .getBody()
                .getAudience();
    }
}