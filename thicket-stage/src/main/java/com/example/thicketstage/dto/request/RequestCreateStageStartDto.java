package com.example.thicketstage.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
public class RequestCreateStageStartDto {

    @NotBlank(message = "공연UUID는 필수 입력 항목입니다.")
    private UUID stageId;

    private List<StageStartDto> stageStartDtos;

    @Data
    @NoArgsConstructor
    public static class StageStartDto {
        private LocalDate date;
        private LocalTime time;
    }
}