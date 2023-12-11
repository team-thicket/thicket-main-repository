package com.example.thicketstage.dto.request;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Getter @Setter @NoArgsConstructor
@AllArgsConstructor @Builder @ToString
public class RequestStageStartUpdateDto {

    private String stageUuid;
    private List<StageStartUpdateDto> stageStartUpdateDtos;

    @Data
    @NoArgsConstructor
    public static class StageStartUpdateDto {
        private LocalDate date;
        private List<LocalTime> times;
    }

}
