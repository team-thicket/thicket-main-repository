package com.example.thicketstage.dto.response;

import com.example.thicketstage.domain.Stage;
import com.example.thicketstage.enumerate.StageType;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ResponseAdminStageDto {

    private StageType stageType;

    private String name;

    private LocalDateTime stageOpen;

    private LocalDateTime stageClose;


    public ResponseAdminStageDto(Stage findStage) {
        this.stageType = findStage.getStageType();
        this.name = findStage.getName();
        this.stageOpen = findStage.getStageOpen();
        this.stageClose = findStage.getStageClose();
    }
}
