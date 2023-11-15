package com.example.thicketmember.service;

import com.example.thicketmember.domain.Member;
import com.example.thicketmember.dto.ResponseMemberDto;
import com.example.thicketmember.enumerate.MemberStatus;
import com.example.thicketmember.repository.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
class MemberServiceImplTest {
    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;

    @BeforeEach
    public void beforeEach() {
        Member member = Member.createMember(
                "홍길동",
                LocalDate.of(2023,11,14),
                "test123@gmail.com",
                "1234",
                MemberStatus.ACTIVE);
        memberRepository.save(member);
    }
    @Test
    void 회원_조회() {
        //given
        String email = "test123@gmail.com";

        //when
        ResponseMemberDto findMember = memberService.getMemberByToken();

        //then
        assertEquals("홍길동", findMember.getName());
        assertEquals(LocalDate.of(2023,11, 14),findMember.getBirth());

    }

    @Test
    void 비밀번호_변경() {
        //given
        String email = "test123@gmail.com";
        String oldPw = "1234";
        String newPw = "4567";
        //when
        memberService.setNewPassword(oldPw, newPw);
        Member findMember = memberRepository.findByEmail(email);
        //then
        assertEquals("4567",findMember.getPassword());

    }
    @Test
    void 비밀번호_변경메서드의_비밀번호_틀림() {
        //given
        String email = "test123@gmail.com";
        String oldPw = "1111";
        String newPw = "4567";
        //when //then
        assertThrows(IllegalArgumentException.class, () -> memberService.setNewPassword(oldPw, newPw));

    }

    @Test
    void 비밀번호_변경_메서드의_새로운_비밀번호가_현재_비밀번호와_같음() {
        //given
        String email = "test123@gmail.com";
        String oldPw = "4567";
        String newPw = "4567";
        //when //then
        assertThrows(IllegalArgumentException.class, () -> memberService.setNewPassword(oldPw, newPw));
    }

    @Test
    void 회원_탈퇴() {
        //given
        String email = "test123@gmail.com";
        String pswd = "1234";

        //when
        memberService.setInactive(pswd);
        Member findMember = memberRepository.findByEmail(email);

        //then
        assertEquals(MemberStatus.INACTIVE,findMember.getStatus());

    }

    @Test
    void 회원_탈퇴_비밀번호_틀림() {
        //given
        String email = "test123@gmail.com";
        String pswd = "4567";

        //when //then
        assertThrows(IllegalArgumentException.class, () -> memberService.setInactive(pswd));

    }
}