package com.example.thicketstage.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
@NoArgsConstructor
public class RequestCreateStageStartDto {

    @NotBlank(message = "공연UUID는 필수 입력 항목입니다.")
    private String stageUuid;

    private List<StageStartDto> stageStartDtos;

    @Data
    @NoArgsConstructor
    public static class StageStartDto {
        private LocalDate date;
        private LocalTime time;
    }
//    public static class StageStartDto {
//        private LocalDate date;
//        private List<LocalTime> times;
//    }
}