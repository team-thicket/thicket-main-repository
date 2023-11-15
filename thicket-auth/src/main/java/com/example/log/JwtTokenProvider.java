package com.example.log;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenProvider {

    @Value("${jwt.properties.secret}")
    private String secret;

    @Value("${jwt.properties.access-token-expiration-seconds}")
    private long accessTokenExpiration;

    @Value("${jwt.properties.refresh-token-expiration-seconds}")
    private long refreshTokenExpiration;

    public String createAccessToken(String username) {
        return JWT.create()
                .withSubject(username)
                .withExpiresAt(new Date(System.currentTimeMillis() + accessTokenExpiration * 1000))
                .sign(Algorithm.HMAC512(secret));
    }

    public String createRefreshToken(String username) {
        return JWT.create()
                .withSubject(username)
                .withExpiresAt(new Date(System.currentTimeMillis() + refreshTokenExpiration * 1000))
                .sign(Algorithm.HMAC512(secret));
    }

    public String verifyToken(String token) {
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC512(secret)).build();
            DecodedJWT jwt = verifier.verify(token);
            return jwt.getSubject();
        } catch (JWTVerificationException exception) {
            throw new RuntimeException("Invalid token.");
        }
    }
}