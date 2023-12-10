package com.example.thicketauth.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestVerificationDto {
        @NotBlank
        private String email;

        @NotBlank
        private String code;
}
