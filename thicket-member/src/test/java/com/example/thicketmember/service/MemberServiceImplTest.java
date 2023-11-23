package com.example.thicketmember.service;

import com.example.thicketmember.domain.Member;
import com.example.thicketmember.dto.request.RequestInactiveDto;
import com.example.thicketmember.dto.request.RequestSetNewPasswordDto;
import com.example.thicketmember.dto.response.ResponseMemberDto;
import com.example.thicketmember.enumerate.MemberRole;
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
                MemberStatus.ACTIVE,
                MemberRole.USER);
        memberRepository.save(member);
    }
    @Test
    void 회원_조회() {
        //given
        String email = "test123@gmail.com";

        //when
        ResponseMemberDto findMember = memberService.getMemberByToken(email);

        //then
        assertEquals("홍길동", findMember.getName());
        assertEquals(LocalDate.of(2023,11, 14),findMember.getBirth());

    }

    @Test
    void 비밀번호_변경() {
        //given
        String email = "test123@gmail.com";
        RequestSetNewPasswordDto dto = new RequestSetNewPasswordDto();
        dto.setCurrentPassword("1234");
        dto.setNewPassword("4567");
        //when
        memberService.setNewPassword(email, dto);
        Member findMember = memberRepository.findByEmail(email);
        //then
        assertEquals("4567",findMember.getPassword());

    }
    @Test
    void 비밀번호_변경메서드의_비밀번호_틀림() {
        //given
        String email = "test123@gmail.com";
        RequestSetNewPasswordDto dto = new RequestSetNewPasswordDto();
        dto.setCurrentPassword("1111");
        dto.setNewPassword("4567");

        //when //then
        assertThrows(IllegalArgumentException.class, () -> memberService.setNewPassword(email, dto));

    }

    @Test
    void 비밀번호_변경_메서드의_새로운_비밀번호가_현재_비밀번호와_같음() {
        //given
        String email = "test123@gmail.com";
        RequestSetNewPasswordDto dto = new RequestSetNewPasswordDto();
        dto.setCurrentPassword("4567");
        dto.setNewPassword("4567");

        //when //then
        assertThrows(IllegalArgumentException.class, () -> memberService.setNewPassword(email, dto));
    }

    @Test
    void 회원_탈퇴() {
        //given
        String email = "test123@gmail.com";
        RequestInactiveDto dto = new RequestInactiveDto();
        dto.setPswd("1234");

        //when
        memberService.setInactive(email, dto);
        Member findMember = memberRepository.findByEmail(email);

        //then
        assertEquals(MemberStatus.INACTIVE,findMember.getStatus());

    }

    @Test
    void 회원_탈퇴_비밀번호_틀림() {
        //given
        String email = "test123@gmail.com";
        RequestInactiveDto dto = new RequestInactiveDto();
        dto.setPswd("4567");

        //when //then
        assertThrows(IllegalArgumentException.class, () -> memberService.setInactive(email, dto));

    }
}