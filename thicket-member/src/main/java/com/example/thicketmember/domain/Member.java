package com.example.thicketmember.domain;

import com.example.thicketmember.enumerate.MemberStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private String name;

    @Column(length = 100, nullable = false)
    private LocalDate birth;

    @Column(length = 100, nullable = false)
    private String email;

    @Column(length = 100, nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private MemberStatus status;
    // 테스트용 메서드
    public static Member createMember(String newName, LocalDate newBirth, String newEmail, String newPassword, MemberStatus newStatus) {
        Member member = new Member();

        member.name = newName;
        member.birth = newBirth;
        member.email = newEmail;
        member.password = newPassword;
        member.status = newStatus;

        return member;
    }
    //비즈니스 메서드
    public void changePassword(String newPassword) {
        password = newPassword;
    }

    public void inactive() {
        status = MemberStatus.INACTIVE;
    }
}
