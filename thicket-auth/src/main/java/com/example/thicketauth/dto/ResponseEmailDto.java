package com.example.thicketauth.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseEmailDto {

    @NotBlank
    private String code;
}