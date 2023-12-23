package com.example.thicketgateway.jwt;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.server.authentication.ServerAuthenticationConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.Set;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationConverter implements ServerAuthenticationConverter {
    private final JwtDecoder jwtDecoder;

    @Override
    public Mono<Authentication> convert(ServerWebExchange exchange) {
        return Mono.justOrEmpty(exchange)
                // 헤더에서 토큰 추출 메서드 호출
                .flatMap(serverWebExchange -> Mono.justOrEmpty(extractToken(serverWebExchange)))
                .flatMap(token -> {
                    log.info("----------토큰 인증 컨버터 호출-----------");
                    Mono<String> roleMono = jwtDecoder.extractMonoRole(token);
                    Mono<String> uuidMono = jwtDecoder.extractMonoUuid(token);
                    // 추출한 토큰에서 uuid, role을 꺼내서
                    // security용 토큰 객체로 컨버팅
                    return Mono.zip(roleMono, uuidMono)
                            .flatMap(tuple -> {
                                String uuid = tuple.getT1();
                                String role = tuple.getT2();
                                log.info("Convert ROLE : " + role);
                                log.info("Convert UUID : " + uuid);
                                Set<SimpleGrantedAuthority> auth = Collections.singleton(new SimpleGrantedAuthority(role));
                                return Mono.just(new UsernamePasswordAuthenticationToken(uuid,token,auth));
                            });
                });
    }

    // 헤더에서 토큰 추출
    public String extractToken(ServerWebExchange exchange) {
        log.info("토큰 추출");
        return exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
    }
}
//userDetails, token, userDetails.getAuthorities()