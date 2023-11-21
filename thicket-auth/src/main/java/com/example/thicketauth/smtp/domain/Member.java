package com.example.thicketauth.smtp.domain;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity // DB의 테이블과 매핑
@Table(name = "users") // 테이블 이름 명명
public class Member {

    @Id // 기본키 매핑
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본키 생성 전략
    private Long id;

    @Column(nullable = false, length = 50, unique=true)
    private String email;
    @Column(nullable = false, length = 50)
    private String name;
    @Column(nullable = false, unique = true)
    private String userId;
    @Column(nullable = false, unique = true)
    private String encryptedPwd;
}
