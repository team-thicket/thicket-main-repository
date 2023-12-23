package com.example.thicketstage.dto.response;

import com.example.thicketstage.domain.Stage;
import com.example.thicketstage.enumerate.StageStatus;
import com.example.thicketstage.enumerate.StageType;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class ResponseAdminStageDto {

    private UUID stageId;

    private StageType stageType;

    private String name;

    private LocalDateTime stageOpen;

    private LocalDateTime stageClose;

    private StageStatus stageStatus;


    public ResponseAdminStageDto(Stage findStage) {
        this.stageId = findStage.getId();
        this.stageType = findStage.getStageType();
        this.name = findStage.getName();
        this.stageOpen = findStage.getStageOpen();
        this.stageClose = findStage.getStageClose();
        this.stageStatus = calculateStageStatus(findStage);
    }

    private StageStatus calculateStageStatus(Stage stage) {
        LocalDateTime now = LocalDateTime.now();

        if (now.isBefore(stage.getStageOpen())){
            return StageStatus.BEFORE;
        } else if (now.isAfter(stage.getStageOpen()) && now.isBefore(stage.getStageClose())) {
            return StageStatus.ONGOING;
        }else {
            return StageStatus.ENDED;
        }
    }
}