package com.example.thicketmember.service;

import com.example.thicketmember.domain.Member;
import com.example.thicketmember.enumerate.MemberStatus;
import com.example.thicketmember.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberServiceImpl implements MemberService{
    private final MemberRepository memberRepository;
    @Override
    public ResponseEntity<?> getMemberByEmail(String email) {
        // 인증인가 서버와 연동 했을때 토큰에서 PK Id 값을 추출해서 진행
        Long tokenId = 1L;
        Optional<Member> optionalMember = memberRepository.findById(tokenId);

        if (optionalMember.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("존재하지 않는 회원입니다.");
        }

        return ResponseEntity.status(HttpStatus.OK)
                .body(optionalMember.get());
    }

    @Override
//    @Transactional
    public ResponseEntity<?> setNewPassword(String oldPw, String newPw) {
        // 인증인가 서버와 연동 했을때 토큰에서 PK Id 값을 추출해서 진행
        Long tokenId = 1L;
        Optional<Member> optionalMember = memberRepository.findById(tokenId);

        if (optionalMember.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("해당 Id값의 멤버가 없습니다");
        }

        Member member = optionalMember.get();
        if (!oldPw.equals(member.getPassword())) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("현재 비밀번호가 일치하지 않습니다.");
        }
        if (oldPw.equals(newPw)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("새로운 비밀번호를 입력해주세요.");
        }

        member.changePassword(newPw);

        return ResponseEntity.status(HttpStatus.OK).body(member);
    }

    @Override
//    @Transactional
    public ResponseEntity<?> setInactive(String pswd) {
        // 인증인가 서버와 연동 했을때 토큰에서 PK Id 값을 추출해서 진행
        Long tokenId = 1L;
        Optional<Member> optionalMember = memberRepository.findById(tokenId);

        if (optionalMember.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("존재하지 않는 회원입니다.");
        }

        Member member = optionalMember.get();

        if (!member.getPassword().equals(pswd)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("비밀번호가 일치하지 않습니다.");
        }

        if (member.getStatus().equals(MemberStatus.INACTIVE)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("이미 탈퇴된 회원입니다.");
        }

        // Call the deactivate method to update the state
        member.inactive();

        return ResponseEntity.status(HttpStatus.OK).body("정상적으로 탈퇴하였습니다.");
    }
}
