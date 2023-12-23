package com.example.thicketstage.dto.response;

import com.example.thicketstage.domain.Stage;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class ResponseStageThumbnailDto {

    private UUID stageId;
    private String name;
    private String place;
    private LocalDateTime stageOpen;
    private LocalDateTime stageClose;
    private LocalDateTime ticketOpen;
    private String posterImg;

    public ResponseStageThumbnailDto(Stage findStage) {
        this.stageId = findStage.getId();
        this.name = findStage.getName();
        this.place = findStage.getPlace();
        this.stageOpen = findStage.getStageOpen();
        this.stageClose = findStage.getStageClose();
        this.ticketOpen = findStage.getTicketOpen();
        this.posterImg = findStage.getPosterImg();
    }
}