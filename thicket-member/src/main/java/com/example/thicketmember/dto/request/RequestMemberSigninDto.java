package com.example.thicketmember.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RequestMemberSigninDto {
    private String email;
    private String password;
}
