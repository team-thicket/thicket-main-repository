package com.example.thicketauth.service;

import com.example.thicketauth.entity.Role;
import com.example.thicketauth.exception.EmailDuplicateException;
import com.example.thicketauth.entity.Password;
import com.example.thicketauth.dto.RequestMemberSignup;
import com.example.thicketauth.dto.ResponseMemberSignup;
import com.example.thicketauth.entity.Member;
import com.example.thicketauth.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberSignupService {

    private final MemberRepository memberRepository;

    @Transactional
    public ResponseMemberSignup signup(RequestMemberSignup request) {
        if (memberRepository.existsByEmail(request.getEmail())) {
            throw new EmailDuplicateException(request.getEmail());
        }

        Role role = Optional.ofNullable(request.getRole()).orElse(Role.USER);

        Member member = Member.builder()
                .email(request.getEmail())
                .password(new Password(request.getPassword()))
                .name(request.getName())
                .role(role)
                .birthdate(request.getBirthdate())
                .phoneNumber(request.getPhoneNumber())
                .build();

        memberRepository.save(member);
        System.out.println("Member is saved: " + member);

        return ResponseMemberSignup.from(member);
    }
}