package com.example.thicketauth.service;

import com.example.thicketauth.dto.ResponseMemberDto;
import com.example.thicketauth.entity.Member;
import com.example.thicketauth.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public ResponseMemberDto getMemberInfo(String email) {
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("해당 이메일의 사용자를 찾을 수 없습니다."));
        return ResponseMemberDto.from(member);
    }

    public void changePassword(String email, String currentPassword, String newPassword) {
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("해당 이메일의 사용자를 찾을 수 없습니다."));

        if (!member.getPassword().isMatches(currentPassword)) {
            throw new BadCredentialsException("현재 비밀번호가 일치하지 않습니다.");
        }

        member.getPassword().changePassword(currentPassword, newPassword);
        memberRepository.save(member);
    }

    public void deleteMember(String email, String password) {
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("해당 이메일의 사용자를 찾을 수 없습니다."));

        if (!member.getPassword().isMatches(password)) {
            throw new BadCredentialsException("비밀번호가 일치하지 않습니다.");
        }

    }
}