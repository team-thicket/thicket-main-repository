package com.example.thicketstage.dto.response;

import com.example.thicketstage.domain.Stage;
import com.example.thicketstage.enumerate.StageStatus;
import com.example.thicketstage.enumerate.StageType;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ResponseStageDto {

    private String stageUuid;

    private String name;

    private String place;

    private LocalDateTime ticketOpen;

    private LocalDateTime stageOpen;

    private LocalDateTime stageClose;

    private LocalDateTime lastTicket;

    private String runningTime;

    private String ageLimit;

    private StageType stageType;

    private StageStatus stageStatus;

    private String posterImg;

    private String detailPosterImg;

    private String stageInfo;

    public ResponseStageDto(Stage stage) {
        this.stageUuid = stage.getUuid();
        this.name = stage.getName();
        this.place= stage.getPlace();
        this.ticketOpen = stage.getTicketOpen();
        this.stageOpen = stage.getStageOpen();
        this.stageClose = stage.getStageClose();
        this.lastTicket = stage.getLastTicket();
        this.runningTime = stage.getRunningTime();
        this.ageLimit = stage.getAgeLimit();
        this.stageType = stage.getStageType();
        this.stageStatus = stage.getStageStatus();
        this.posterImg = stage.getPosterImg();
        this.detailPosterImg = stage.getDetailPosterImg();
        this.stageInfo = stage.getStageInfo();
    }
}