package com.example.log.service;

import com.example.log.exception.EmailDuplicateException;
import com.example.log.entity.Password;
import com.example.log.dto.MemberSignupRequest;
import com.example.log.dto.MemberSignupResponse;
import com.example.log.entity.Member;
import com.example.log.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class MemberSignupService {

    private final MemberRepository memberRepository;

    @Transactional
    public MemberSignupResponse signup(MemberSignupRequest request) {
        if (memberRepository.existsByEmail(request.getEmail())) {
            throw new EmailDuplicateException(request.getEmail());
        }

        Member member = Member.builder()
                .email(request.getEmail())
                .password(new Password(request.getPassword()))
                .name(request.getName())  // name 필드 추가
                .role(request.getRole())  // role 필드 추가
                .birthdate(request.getBirthdate())
                .build();

        memberRepository.save(member);
        System.out.println("Member is saved: " + member);

        return MemberSignupResponse.from(member);
    }
}