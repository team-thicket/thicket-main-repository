package com.example.thicketauth.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RequestChangePasswordDto {

    private String currentPassword;
    private String newPassword;
}