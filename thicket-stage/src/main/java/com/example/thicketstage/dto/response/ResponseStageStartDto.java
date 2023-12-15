package com.example.thicketstage.dto.response;

import com.example.thicketstage.domain.StageStart;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class ResponseStageStartDto {

    private String stageUuid;

    private LocalDate date;

    private LocalTime time;


public ResponseStageStartDto(StageStart stageStart) {
    this.stageUuid = stageStart.getUuid();
    this.date = stageStart.getDate();
    this.time = stageStart.getTime();
}
}