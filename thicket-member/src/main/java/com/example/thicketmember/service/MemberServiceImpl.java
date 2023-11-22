package com.example.thicketmember.service;

import com.example.thicketmember.domain.Member;
import com.example.thicketmember.dto.request.RequestInactiveDto;
import com.example.thicketmember.dto.request.RequestSetNewPasswordDto;
import com.example.thicketmember.dto.response.ResponseMemberDto;
import com.example.thicketmember.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberServiceImpl implements MemberService{
    private final MemberRepository memberRepository;
    @Override
    public ResponseMemberDto getMemberByToken(String email) {
        // 인증인가 서버와 연동 했을때 인증 인가 서버에서 토큰을 이메일로 변환해서 보내준다.
        // 이미 인증/인가 서버에서 회원의 존재 여부가 검증 되었기 때문에
        // 존재여부를 검사할 필요가 없어졌다.

        Member findMember = memberRepository.findByEmail(email);

        return ResponseMemberDto.toDto(findMember);
    }

    @Override
    @Transactional
    public void setNewPassword(String email, RequestSetNewPasswordDto dto) {
        // 인증인가 서버와 연동 했을때 인증 인가 서버에서 토큰을 이메일로 변환해서 보내준다.
        // 이미 인증/인가 서버에서 회원의 존재 여부가 검증 되었기 때문에
        // 존재여부를 검사할 필요가 없어졌다.
        Member findMember = memberRepository.findByEmail(email);

        if (!dto.getOldPw().equals(findMember.getPassword())) {
            throw new IllegalArgumentException("현재 비밀번호가 일치하지 않습니다.");
        }

        if (dto.getOldPw().equals(dto.getNewPw())) {
            throw new IllegalArgumentException("새로운 비밀번호를 입력해 주세요.");
        }

        findMember.changePassword(dto.getNewPw());
    }

    @Override
    @Transactional
    public void setInactive(String email, RequestInactiveDto dto) {
        // 인증인가 서버와 연동 했을때 인증 인가 서버에서 토큰을 이메일로 변환해서 보내준다.
        // 이미 인증/인가 서버에서 회원의 존재 여부가 검증 되었기 때문에
        // 존재여부를 검사할 필요가 없어졌다.
        Member findMember = memberRepository.findByEmail(email);

        if (!findMember.getPassword().equals(dto.getPswd())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        findMember.inactive();
    }

    @Override
    @Transactional
    public void setAdmin(String email, RequestInactiveDto dto) {
        Member findMember = memberRepository.findByEmail(email);

        if (!findMember.getPassword().equals(dto.getPswd())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        findMember.changeAdmin();
    }
}
