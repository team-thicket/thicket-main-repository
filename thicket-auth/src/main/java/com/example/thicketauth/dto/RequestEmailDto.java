package com.example.thicketauth.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import org.springframework.validation.annotation.Validated;

@Getter
public class RequestEmailDto {
    @NotBlank
    private String email;
}