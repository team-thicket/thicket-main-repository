package com.example.thicketmember.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class RequestSetNewPasswordDto {
    String oldPw;
    String newPw;
}
