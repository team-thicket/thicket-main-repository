package com.example.thicketauth.smtp.config.security;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        return http
       			.httpBasic(AbstractHttpConfigurer::disable)
			    .csrf(AbstractHttpConfigurer::disable)
			    .cors(AbstractHttpConfigurer::disable)
                .headers(header -> header.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))
                     .authorizeHttpRequests(requests ->
                             requests.requestMatchers(antMatcher("/**")).permitAll()	// requestMatchers의 인자로 전달된 url은 모두에게 허용
                                     .requestMatchers(PathRequest.toH2Console()).permitAll()	// H2 콘솔 접속은 모두에게 허용
                             //        .anyRequest().authenticated()	// 그 외의 모든 요청은 인증 필요
                     )
                     .sessionManagement(sessionManagement ->
                             sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                     )// 세션을 사용하지 않으므로 STATELESS 설정
                     .build();
    }


}


