package com.example.thicketmember;

import com.example.thicketmember.domain.Member;
import com.example.thicketmember.enumerate.MemberRole;
import com.example.thicketmember.enumerate.MemberStatus;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class Init {
    private final InitService initService;

    @PostConstruct
    public void init(){
        initService.init();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {
        private final EntityManager em;
        private final PasswordEncoder pe;
        public void init() {
            Member member1 = Member.createMember(
                    "홍길동",
                    LocalDate.of(2023, 11, 14),
                    "test123@gmail.com",
                    pe.encode("a12345678!"),
                    "010-1234-5678",
                    MemberRole.USER);
            em.persist(member1);

            Member member2 = Member.createMember(
                    "공연_관리자",
                    LocalDate.of(2023,1,5),
                    "test1234@gmail.com",
                    pe.encode("a12345678!"),
                    "010-1598-4859",
                    MemberRole.ADMIN);
            em.persist(member2);

            Member member3 = Member.createMember(
                    "사이트_운영자",
                    LocalDate.of(2023,2,18),
                    "test1235@gmail.com",
                    pe.encode("a12345678!"),
                    "010-1265-4985",
                    MemberRole.MASTER);
            em.persist(member3);

            Member member4 = Member.createMember(
                    "회원탈퇴용",
                    LocalDate.of(2023,2,18),
                    "test1237895@gmail.com",
                    pe.encode("a12345678!"),
                    "010-1265-4985",
                    MemberRole.USER);
            em.persist(member4);

            Member member5 = Member.createMember(
                    "로그인용",
                    LocalDate.of(2023,2,18),
                    "test123485@gmail.com",
                    pe.encode("a12345678!"),
                    "010-1265-4985",
                    MemberRole.USER);
            em.persist(member5);
        }
    }
}
