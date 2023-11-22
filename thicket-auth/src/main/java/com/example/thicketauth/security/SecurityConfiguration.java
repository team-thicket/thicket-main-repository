package com.example.thicketauth.security;


import com.example.thicketauth.handler.LoginFailureHandler;
import com.example.thicketauth.handler.LoginSuccessHandler;
import com.example.thicketauth.jwt.JsonLoginProcessingFilter;
import com.example.thicketauth.jwt.JwtAuthenticationFilter;
import com.example.thicketauth.jwt.JwtTokenProvider;
import com.example.thicketauth.repository.MemberRepository;
import com.example.thicketauth.service.UserDetailsServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity(debug = true)
@Configuration
public class SecurityConfiguration {

    private final JwtTokenProvider jwtTokenProvider;
    private final ObjectMapper objectMapper;
    private final MemberRepository memberRepository;
    private final ObjectPostProcessor<Object> objectPostProcessor;

    @Autowired
    public SecurityConfiguration(JwtTokenProvider jwtTokenProvider, ObjectMapper objectMapper, MemberRepository memberRepository, ObjectPostProcessor<Object> objectPostProcessor) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.objectMapper = objectMapper;
        this.memberRepository = memberRepository;
        this.objectPostProcessor = objectPostProcessor;
    }

    @Bean
    @ConditionalOnProperty(name = "spring.h2.console.enabled", havingValue = "true")
    public WebSecurityCustomizer configureH2ConsoleEnable() {
        return web -> web.ignoring()
                .requestMatchers(PathRequest.toH2Console());
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .requestMatchers(
                        new AntPathRequestMatcher("/members/join", HttpMethod.POST.name()))
                .permitAll()
                .anyRequest().authenticated()
                .and()
                .csrf().disable()
                .requestCache().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilterBefore(new JwtAuthenticationFilter(authenticationManager(), jwtTokenProvider), UsernamePasswordAuthenticationFilter.class);
        http.addFilterAt(loginProcessingFilter(authenticationManager(), authenticationSuccessHandler(jwtTokenProvider), authenticationFailureHandler()), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public AbstractAuthenticationProcessingFilter loginProcessingFilter(
            AuthenticationManager authenticationManager,
            AuthenticationSuccessHandler successHandler,
            AuthenticationFailureHandler failureHandler) {
        JsonLoginProcessingFilter jsonLoginProcessingFilter = new JsonLoginProcessingFilter(objectMapper);
        jsonLoginProcessingFilter.setAuthenticationManager(authenticationManager);
        jsonLoginProcessingFilter.setAuthenticationSuccessHandler(successHandler);
        jsonLoginProcessingFilter.setAuthenticationFailureHandler(failureHandler);
        jsonLoginProcessingFilter.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/members/login", HttpMethod.POST.name()));
        return jsonLoginProcessingFilter;
    }
    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        AuthenticationManagerBuilder builder = new AuthenticationManagerBuilder(objectPostProcessor);

        // UserDetailsService, PasswordEncoder 설정
        builder.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
        return builder.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsServiceImpl(memberRepository);
    }

    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler(JwtTokenProvider jwtTokenProvider) {
        return new LoginSuccessHandler(jwtTokenProvider);
    }

    @Bean
    public AuthenticationFailureHandler authenticationFailureHandler() {
        return new LoginFailureHandler(objectMapper);
    }
}