package com.example.log.security;


import com.example.log.JsonLoginProcessingFilter;
import com.example.log.LoginFailureHandler;
import com.example.log.LoginSuccessHandler;
import com.example.log.UserDetailsServiceImpl;
import com.example.log.repository.MemberRepository;
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

    @Autowired
    public SecurityConfiguration(ObjectMapper objectMapper, ObjectPostProcessor<Object> objectPostProcessor, MemberRepository memberRepository) {
        this.objectMapper = objectMapper;
        this.objectPostProcessor = objectPostProcessor;
        this.memberRepository = memberRepository;
    }

    private final ObjectMapper objectMapper;
    private final ObjectPostProcessor<Object> objectPostProcessor;
    private final MemberRepository memberRepository;

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
                        new AntPathRequestMatcher("/member", HttpMethod.POST.name()))
                .permitAll()
                .anyRequest().authenticated()
                .and()
                .csrf().disable()
                .requestCache().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilterAt(loginProcessingFilter(authenticationManager(), authenticationSuccessHandler(), authenticationFailureHandler()),
                UsernamePasswordAuthenticationFilter.class);

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
    public AuthenticationSuccessHandler authenticationSuccessHandler() {
        return new LoginSuccessHandler();
    }

    @Bean
    public AuthenticationFailureHandler authenticationFailureHandler() {
        return new LoginFailureHandler(objectMapper);
    }

}