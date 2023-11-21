package com.example.thicketauth.smtp.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseEmailDto {

    @NotBlank
    private String code;
}