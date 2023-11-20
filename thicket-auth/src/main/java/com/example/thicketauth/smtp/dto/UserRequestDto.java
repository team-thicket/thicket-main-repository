package com.example.thicketauth.smtp.dto;

import lombok.Data;

import java.util.Date;

@Data
public class UserRequestDto {
    private String email;
    private String name;
    private String pwd;
    private String userId;
    private Date createAt;

    private String encryptedPwd;
}
