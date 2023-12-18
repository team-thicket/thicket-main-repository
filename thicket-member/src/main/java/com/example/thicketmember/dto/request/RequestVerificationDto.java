package com.example.thicketmember.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
public class RequestVerificationDto {
        @Email
        @NotBlank(message = "이메일은 필수 입니다.")
        private String email;

        @NotBlank(message = "인증번호를 입력해주세요.")
        private String code;
}
