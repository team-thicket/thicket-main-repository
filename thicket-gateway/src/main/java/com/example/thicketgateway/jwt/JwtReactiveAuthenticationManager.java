package com.example.thicketgateway.jwt;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.Set;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtReactiveAuthenticationManager implements ReactiveAuthenticationManager {
    private final JwtDecoder jwtDecoder;
    @Override
    public Mono<Authentication> authenticate(Authentication authentication) {
        log.info("--------------리액티브 인증 매니저 호출--------------");

        String token = authentication.getCredentials().toString();
        String role = jwtDecoder.tokenToRole(token);
        log.info("토큰에서 ROLE 추출 : " + role);

        String uuid = jwtDecoder.tokenToId(token);
        log.info("토큰에서 UUID 추출 : " + uuid);

        if (role == null || !jwtDecoder.expiredToken(token)) {
            return Mono.empty();
        }

        Set<SimpleGrantedAuthority> auth = Collections.singleton(new SimpleGrantedAuthority(role));

        return Mono.just(new UsernamePasswordAuthenticationToken(uuid, token, auth));
    }
}
