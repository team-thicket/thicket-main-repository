package com.example.thicketauth.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true, nullable = false, updatable = false)
    private String email;

    @AttributeOverride(name = "rawPassword", column = @Column(name = "password", nullable = false))
    @Embedded
    private Password password;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private LocalDate birthdate;


    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserStatus status;

    @Column(nullable = false)
    private String phoneNumber;

    @Builder
    public Member(String email, Password password, String name, LocalDate birthdate , Role role, String phoneNumber) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.birthdate = birthdate;
        this.role = role;
        this.status = UserStatus.ACTIVE;
        this.phoneNumber = phoneNumber;
    }
}