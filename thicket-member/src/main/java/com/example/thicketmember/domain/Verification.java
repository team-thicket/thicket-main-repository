package com.example.thicketmember.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Random;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Verification {
    @Id
    private String email;

    private String code;

    private LocalDateTime expirationDate;

    // 비즈니스 메서드
    public static Verification createCode(String newEmail){
        Verification verification = new Verification();

        Random random = new Random();
        StringBuilder key = new StringBuilder();

        for (int i = 0; i < 8; i++) {
            int index = random.nextInt(4);
            switch (index) {
                case 0: key.append((char) (random.nextInt(26) + 97)); break;
                case 1: key.append((char) (random.nextInt(26) + 65)); break;
                default: key.append(random.nextInt(9));
            }
        }
        verification.email = newEmail;
        verification.code = key.toString();
        verification.expirationDate = LocalDateTime.now().plusMinutes(5);

        return verification;
    }
}
