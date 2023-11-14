package com.example.log.dto;

import com.example.log.entity.Role;
import lombok.*;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import java.time.LocalDate;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@AllArgsConstructor
public class MemberSignupRequest {

    @Email
    private String email;

    @NotBlank
    private String password;

    private String name;

    private LocalDate birthdate;

    private Role role;

}