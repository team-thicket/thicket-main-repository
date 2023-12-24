package com.example.thicketstage.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.UUID;

@Getter @Setter @NoArgsConstructor
@AllArgsConstructor @Builder @ToString
public class RequestDeleteStageDto {

    @NotNull
    private UUID id;
}