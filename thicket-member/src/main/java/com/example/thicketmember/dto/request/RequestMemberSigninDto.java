package com.example.thicketmember.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RequestMemberSigninDto {
    @Email(message = "계정 정보를 확인해주세요.")
    @NotBlank(message = "계정 정보를 확인해주세요.")
    private String email;

    @Pattern(regexp = "^(?=.[a-zA-Z])(?=.\\d)[a-zA-Z\\d]{8,10}$", message = "계정 정보를 확인해주세요.")
    @NotBlank(message = "계정 정보를 확인해주세요.")
    private String password;
}
