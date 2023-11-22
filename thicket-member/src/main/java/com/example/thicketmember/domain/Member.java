package com.example.thicketmember.domain;

import com.example.thicketmember.TimeStamp;
import com.example.thicketmember.enumerate.MemberRole;
import com.example.thicketmember.enumerate.MemberStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends TimeStamp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 11, nullable = false)
    private String name;

    @Column(nullable = false)
    private LocalDate birth;

    @Column(length = 225, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private MemberStatus status;

    @Enumerated(EnumType.STRING)
    private MemberRole memberRole;

    // 테스트용 메서드
    public static Member createMember(String newName,
                                      LocalDate newBirth,
                                      String newEmail,
                                      String newPassword,
                                      MemberStatus newStatus,
                                      MemberRole newMemberRole) {
        Member member = new Member();

        member.name = newName;
        member.birth = newBirth;
        member.email = newEmail;
        member.password = newPassword;
        member.status = newStatus;
        member.memberRole = newMemberRole;

        return member;
    }
    //비즈니스 메서드
    public void changePassword(String newPassword) {
        password = newPassword;
    }

    public void inactive() {
        status = MemberStatus.INACTIVE;
    }

    public void changeAdmin() { memberRole = MemberRole.ADMIN; }
}
