package com.example.thicketstage.dto.response;

import com.example.thicketstage.domain.StageStart;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Data
public class ResponseStageStartDto {

    private UUID stageId;

    private LocalDate date;

    private LocalTime time;


public ResponseStageStartDto(StageStart stageStart) {
    this.stageId = stageStart.getId();
    this.date = stageStart.getDate();
    this.time = stageStart.getTime();
}
}