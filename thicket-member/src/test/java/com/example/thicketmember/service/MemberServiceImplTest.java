package com.example.thicketmember.service;

import com.example.thicketmember.domain.Member;
import com.example.thicketmember.dto.request.*;
import com.example.thicketmember.dto.response.ResponseMemberDto;
import com.example.thicketmember.dto.response.ResponseMemberDtoForAdmin;
import com.example.thicketmember.enumerate.MemberRole;
import com.example.thicketmember.enumerate.MemberStatus;
import com.example.thicketmember.repository.MemberRepository;
import com.sun.jdi.request.DuplicateRequestException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Transactional
class MemberServiceImplTest {
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    MemberService memberService;
    @Autowired
    PasswordEncoder pe;

    @Test
    void 회원가입() {
        //given
        String name = "홍길동";
        LocalDate birth = LocalDate.of(2023,11,14);
        String email = "testCode@gmail.com";
        String password = "1234";

        RequestMemberSignupDto dto = new RequestMemberSignupDto();
        dto.setName(name);
        dto.setBirthdate(birth);
        dto.setEmail(email);
        dto.setPassword(password);

        //when
        Member signup = memberService.signup(dto);
        Member member = memberRepository.findById(signup.getId()).get();

        //then
        assertThat(member.getName()).isEqualTo(name);
        assertThat(member.getBirth()).isEqualTo(birth);
        assertThat(member.getEmail()).isEqualTo(email);
        assertTrue(pe.matches(password, member.getPassword()));
        assertThat(member.getStatus()).isEqualTo(MemberStatus.ACTIVE);
        assertThat(member.getMemberRole()).isEqualTo(MemberRole.USER);

    }
    @Test
    void 이미_가입된_회원입니다() {
        //given
        String name = "홍길동";
        LocalDate birth = LocalDate.of(2023,11,14);
        String email = "test123@gmail.com";
        String password = "12345678!";

        RequestMemberSignupDto dto = new RequestMemberSignupDto();
        dto.setName(name);
        dto.setBirthdate(birth);
        dto.setEmail(email);
        dto.setPassword(password);

        //when & then
        assertThrows(DuplicateRequestException.class, () -> memberService.signup(dto));
    }
    @Test
    void 로그인() {
        //given
        String email = "test123485@gmail.com";
        String password = "a12345678!";
        RequestMemberSigninDto dto = new RequestMemberSigninDto();
        dto.setEmail(email);
        dto.setPassword(password);

        //when
        Member signin = memberService.signin(dto);
        Member findMember = memberRepository.findById(signin.getId()).get();

        //then
        assertThat(signin).isEqualTo(findMember);

    }

    @Test
    void 계정_정보를_확인해주세요_1() {
        //given
        String email = "wrongEmail@naver.com";
        String password = "12345678!";
        RequestMemberSigninDto dto = new RequestMemberSigninDto();
        dto.setEmail(email);
        dto.setPassword(password);

        //when & then
        assertThrows(IllegalArgumentException.class, () -> memberService.signin(dto));
    }

    @Test
    void 계정_정보를_확인해주세요_2() {
        //given
        String email = "test123@gmail.com";
        String password = "wrongPassword";
        RequestMemberSigninDto dto = new RequestMemberSigninDto();
        dto.setEmail(email);
        dto.setPassword(password);

        //when & then
        assertThrows(IllegalArgumentException.class,
                () -> memberService.signin(dto));
    }

    @Test
    void 회원_정보_조회() {
        //given
        Member member = memberRepository.findAll().get(0);

        //when
        ResponseMemberDto findMember = memberService.findMember(String.valueOf(member.getId()));

        //then
        assertThat(member.getName()).isEqualTo(findMember.getName());
        assertThat(member.getBirth()).isEqualTo(findMember.getBirth());
        assertThat(member.getEmail()).isEqualTo(findMember.getEmail());
        assertThat(member.getPhone()).isEqualTo(findMember.getPhoneNumber());

    }

    @Test
    void 회원_목록_조회() {
        //given & when
        List<ResponseMemberDtoForAdmin> members = memberService.findMembers();

        //then
        assertThat(members.size()).isEqualTo(3);

    }

    @Test
    void 비밀번호_변경() {
        //given
        Member member = memberRepository.findAll().get(0);
        String findId = String.valueOf(member.getId());
        String cPw = "a12345678!";
        String nPw = "newPassword";
        RequestChangePasswordDto dto = new RequestChangePasswordDto();
        dto.setCurrentPassword(cPw);
        dto.setNewPassword(nPw);

        //when
        memberService.changePassword(findId,dto);
        Member findMember = memberRepository.findById(UUID.fromString(findId)).get();

        //then
        assertTrue(pe.matches(nPw, findMember.getPassword()));
    }
    @Test
    void 비밀번호_변경시_비밀번호_틀림() {
        //given
        Member member = memberRepository.findAll().get(0);
        String findId = String.valueOf(member.getId());
        String cPw = "wrongPassword";
        String nPw = "newPassword";
        RequestChangePasswordDto dto = new RequestChangePasswordDto();
        dto.setCurrentPassword(cPw);
        dto.setNewPassword(nPw);

        //when
        memberService.changePassword(findId,dto);
        Member findMember = memberRepository.findById(UUID.fromString(findId)).get();

        //then
        assertTrue(pe.matches(nPw, findMember.getPassword()));


    }
    
    @Test
    void 비밀번호_변경시_이전_비밀번호와_같음() {
        //given
        Member member = memberRepository.findAll().get(0);
        String findId = String.valueOf(member.getId());
        String cPw = "a12345678!";
        String nPw = "a12345678!";
        RequestChangePasswordDto dto = new RequestChangePasswordDto();
        dto.setCurrentPassword(cPw);
        dto.setNewPassword(nPw);

        //when
        memberService.changePassword(findId,dto);
        Member findMember = memberRepository.findById(UUID.fromString(findId)).get();

        //then
        assertTrue(pe.matches(nPw, findMember.getPassword()));
    
    }

    @Test
    void 회원탈퇴() {
        //given
        Member member = memberRepository.findAll().get(0);
        String findId = String.valueOf(member.getId());

        String pw = "a12345678!";
        RequestWithdrawDto dto = new RequestWithdrawDto();
        dto.setPassword(pw);

        //when
        memberService.withdraw(findId,dto);
        Member findMember = memberRepository.findById(UUID.fromString(findId)).get();

        //then
        assertThat(findMember.getStatus()).isEqualTo(MemberStatus.INACTIVE);

    }
    
    @Test
    void 회원_탈퇴시_비밀번호_틀림() {
        //given
        Member member = memberRepository.findAll().get(0);
        String findId = String.valueOf(member.getId());

        String pw = "wrongPassword!";
        RequestWithdrawDto dto = new RequestWithdrawDto();
        dto.setPassword(pw);

        //when & then
        assertThrows(IllegalArgumentException.class, () -> memberService.withdraw(findId, dto));

    }

    @Test
    void 관리자로_승급() {
        //given
        Member member = memberRepository.findAll().get(0);
        String findId = String.valueOf(member.getId());

        String businessCode = "123-45-67891";
        RequestChangeMemberRoleDto dto = new RequestChangeMemberRoleDto();
        dto.setBusinessCode(businessCode);

        //when
        memberService.changeMemberRole(findId, dto);
        Member findMember = memberRepository.findById(UUID.fromString(findId)).get();

        //then
        assertThat(findMember.getBusinessCode()).isEqualTo(businessCode);

    }
}