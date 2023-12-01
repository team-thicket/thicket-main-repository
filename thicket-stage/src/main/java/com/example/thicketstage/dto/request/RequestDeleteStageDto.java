package com.example.thicketstage.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter @Setter @NoArgsConstructor
@AllArgsConstructor @Builder @ToString
public class RequestDeleteStageDto {

    @NotNull
    private Long id;

}
