package com.example.thicketmember;

import com.example.thicketmember.domain.Member;
import com.example.thicketmember.enumerate.MemberRole;
import com.example.thicketmember.enumerate.MemberStatus;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
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
        public void init() {
            Member member = Member.createMember(
                    "홍길동",
                    LocalDate.of(2023,11,14),
                    "test123@gmail.com",
                    "a12345678!",
                    MemberStatus.ACTIVE,
                    MemberRole.USER);
            em.persist(member);
        }

    }
}
