package com.example.thicketstage.dto.response;

import com.example.thicketstage.domain.Stage;
import com.example.thicketstage.enumerate.StageType;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Data
public class ResponseStageDto {

    private UUID stageId;

    private String name;

    private String place;

    private LocalDateTime ticketOpen;

    private LocalDateTime stageOpen;

    private LocalDateTime stageClose;

    private LocalDateTime lastTicket;

    private String runningTime;

    private String ageLimit;

    private StageType stageType;

    private String posterImg;

    private List<String> detailPosterImg;

    private String stageInfo;

    public ResponseStageDto(Stage stage) {
        this.stageId = stage.getId();
        this.name = stage.getName();
        this.place= stage.getPlace();
        this.ticketOpen = stage.getTicketOpen();
        this.stageOpen = stage.getStageOpen();
        this.stageClose = stage.getStageClose();
        this.lastTicket = stage.getLastTicket();
        this.runningTime = stage.getRunningTime();
        this.ageLimit = stage.getAgeLimit();
        this.stageType = stage.getStageType();
        this.posterImg = stage.getPosterImg();
        this.detailPosterImg = Arrays.stream(stage.getDetailPosterImg().split("&")).toList();
        this.stageInfo = stage.getStageInfo();
    }
}