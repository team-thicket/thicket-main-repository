package com.example.thicketstage.dto.request;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Getter @Setter @NoArgsConstructor
@AllArgsConstructor @Builder @ToString
public class RequestStageStartUpdateDto {

//    private LocalDate date;
//
//    private List<LocalTime> times = new ArrayList<>();

    private String stageUuid;
    private List<StageStartUpdateDto> stageStartUpdateDtos;

    @Data
    @NoArgsConstructor
    public static class StageStartUpdateDto {
        private LocalDate date;
        private List<LocalTime> times;
    }

}
