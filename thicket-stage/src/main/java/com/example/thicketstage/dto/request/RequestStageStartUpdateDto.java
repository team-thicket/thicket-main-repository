package com.example.thicketstage.dto.request;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

@Getter @Setter @NoArgsConstructor
@AllArgsConstructor @Builder @ToString
public class RequestStageStartUpdateDto {
// // 회차 정보 수정은 추후 고도화 구현시 구현 예정
    private UUID stageId;
    private List<StageStartUpdateDto> stageStartUpdateDtos;

    @Data
    @NoArgsConstructor
    public static class StageStartUpdateDto {
        private LocalDate date;
        private LocalTime time;
    }
}
