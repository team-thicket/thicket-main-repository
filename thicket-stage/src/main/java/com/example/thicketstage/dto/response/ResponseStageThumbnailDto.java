package com.example.thicketstage.dto.response;

import com.example.thicketstage.domain.Stage;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ResponseStageThumbnailDto {

    private String name;
    private String place;
    private LocalDateTime stageOpen;
    private LocalDateTime stageClose;
    private LocalDateTime ticketOpen;
    private String posterImg;

    public ResponseStageThumbnailDto(Stage findStage) {
        this.name = findStage.getName();
        this.place = findStage.getPlace();
        this.stageOpen = findStage.getStageOpen();
        this.stageClose = findStage.getStageClose();
        this.ticketOpen = findStage.getTicketOpen();
        this.posterImg = findStage.getPosterImg();
    }
}