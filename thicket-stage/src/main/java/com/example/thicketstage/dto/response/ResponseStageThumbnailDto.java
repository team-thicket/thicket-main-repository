package com.example.thicketstage.dto.response;

import com.example.thicketstage.domain.Stage;
import com.example.thicketstage.enumerate.StageStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ResponseStageThumbnailDto {

    private String name;
    private String place;
    private LocalDateTime stageOpen;
    private LocalDateTime stageClose;
    private StageStatus stageStatus;
    private String posterImg;


    public ResponseStageThumbnailDto(Stage findStage) {
        this.name = findStage.getName();
        this.place = findStage.getPlace();
        this.stageOpen = findStage.getStageOpen();
        this.stageClose = findStage.getStageClose();
        this.stageStatus = findStage.getStageStatus();
        this.posterImg = findStage.getPosterImg();
    }
}