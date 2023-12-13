package com.example.thicketmember.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
public class RequestVerificationDto {
        @NotBlank
        private String email;

        @NotBlank
        private String code;
}
