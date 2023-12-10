package com.example.thicketstage.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
@NoArgsConstructor
public class RequestCreateStageStartDto {

    private String stageUuid;
    private List<StageStartDto> stageStartDtos;

    @Data
    @NoArgsConstructor
    public static class StageStartDto {
        private LocalDate date;
        private List<LocalTime> times;
    }

}