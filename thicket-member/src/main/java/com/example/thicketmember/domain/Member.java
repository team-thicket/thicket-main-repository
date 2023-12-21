package com.example.thicketmember.domain;

import com.example.thicketmember.TimeStamp;
import com.example.thicketmember.enumerate.MemberRole;
import com.example.thicketmember.enumerate.MemberStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends TimeStamp {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(length = 20, nullable = false)
    private String name;

    @Column(length = 20,nullable = false)
    private LocalDate birth;

    @Column(length = 50, nullable = false, unique = true)
    private String email;

    @Column(length = 100, nullable = false)
    private String password;

    @Column(length = 20, nullable = false)
    private String phone;

    @Column(length = 20, unique = true)
    private String businessCode;

    @Enumerated(EnumType.STRING)
    private MemberStatus status;

    @Enumerated(EnumType.STRING)
    private MemberRole memberRole;

    //비즈니스 메서드
    public static Member createMember(String newName,
                                      LocalDate newBirth,
                                      String newEmail,
                                      String newPassword,
                                      String newPhone,
                                      MemberRole newMemberRole) {
        Member member = new Member();

        member.name = newName;
        member.birth = newBirth;
        member.email = newEmail;
        member.password = newPassword;
        member.phone = newPhone;
        member.status = MemberStatus.ACTIVE;
        member.memberRole = newMemberRole;

        return member;
    }

    public void changePassword(String newPassword) {
        password = newPassword;
    }

    public void changeStatus(MemberStatus newStatus) {
        status = newStatus;
    }

    public void changeAdmin(String newBusinessCode) {
        memberRole = MemberRole.ROLE_ADMIN;
        businessCode = newBusinessCode;
    }
}
