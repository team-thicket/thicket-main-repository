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
    public ResponseMemberDto getMemberByToken() {
        // 인증인가 서버와 연동 했을때 토큰에서 PK Id 값을 추출해서 진행
        // 이미 인증/인가 서버에서 회원의 존재 여부가 검증 되었기 때문에
        // 존재여부를 검사할 필요가 없어졌음.


        Member findMember = memberRepository.findByEmail("test123@gmail.com");

        return ResponseMemberDto.toDto(findMember);
    }

    @Override
    @Transactional
    public void setNewPassword(RequestSetNewPasswordDto dto) {
        // 인증인가 서버와 연동 했을때 토큰에서 PK Id 값을 추출해서 진행
        Member findMember = memberRepository.findByEmail("test123@gmail.com");

        if (!dto.getOldPw().equals(findMember.getPassword())) {
            //예외 던지기로 변경
            throw new IllegalArgumentException("현재 비밀번호가 일치하지 않습니다.");
        }

        if (dto.getOldPw().equals(dto.getNewPw())) {
            //예외 던지기로 변경
            throw new IllegalArgumentException("새로운 비밀번호를 입력해 주세요.");
        }

        findMember.changePassword(dto.getNewPw());
    }

    @Override
    @Transactional
    public void setInactive(RequestInactiveDto dto) {
        // 인증인가 서버와 연동 했을때 토큰에서 PK Id 값을 추출해서 진행
        Member findMember = memberRepository.findByEmail("test123@gmail.com");

        if (!findMember.getPassword().equals(dto.getPswd())) {
            //IllegalArgumentException으로 처리
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        findMember.inactive();
    }
}
