package com.example.thicketauth.jwt;

import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


import java.io.IOException;
import java.util.Map;
import java.util.Objects;
import com.fasterxml.jackson.databind.ObjectMapper;
public class JsonLoginProcessingFilter extends AbstractAuthenticationProcessingFilter {

    public static final String DEFAULT_USERNAME_KEY = "email";
    public static final String DEFAULT_PASSWORD_KEY = "password";
    private static final AntPathRequestMatcher DEFAULT_ANT_PATH_REQUEST_MATCHER =
            new AntPathRequestMatcher("/login", "POST");

    private final ObjectMapper objectMapper;
    private String usernameParameter = DEFAULT_USERNAME_KEY;
    private String passwordParameter = DEFAULT_PASSWORD_KEY;
    private boolean postOnly = true;

    public JsonLoginProcessingFilter(ObjectMapper objectMapper) {
        super(DEFAULT_ANT_PATH_REQUEST_MATCHER);
        this.objectMapper = objectMapper;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException, IOException {
        if (this.postOnly && !request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException(
                    "Authentication method not supported: " + request.getMethod());
        }

        if (!request.getContentType().equals(MediaType.APPLICATION_JSON_VALUE)) {
            throw new AuthenticationServiceException(
                    "Authentication content-type not supported: " + request.getContentType());
        }

        ServletInputStream inputStream = request.getInputStream();
        Map<String, String> usernamePasswordMap = objectMapper.readValue(inputStream, Map.class);

        String username = obtainParameter(usernameParameter, usernamePasswordMap);
        String password = obtainParameter(passwordParameter, usernamePasswordMap);

        UsernamePasswordAuthenticationToken authRequest =
                new UsernamePasswordAuthenticationToken(username, password);

        return getAuthenticationManager().authenticate(authRequest);
    }

    private String obtainParameter(String parameter, Map<String, String> usernamePasswordMap) {
        String value = usernamePasswordMap.get(parameter);
        if (Objects.isNull(value)) {
            return "";
        }

        return value;
    }

    public void setAuthenticationSuccessHandler(AuthenticationSuccessHandler successHandler) {
        super.setAuthenticationSuccessHandler(successHandler);
    }

    public void setAuthenticationFailureHandler(AuthenticationFailureHandler failureHandler) {
        super.setAuthenticationFailureHandler(failureHandler);
    }

}