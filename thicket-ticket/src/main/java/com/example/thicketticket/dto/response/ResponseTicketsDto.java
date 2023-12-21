package com.example.thicketticket.dto.response;

import com.example.thicketticket.domain.Ticket;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class ResponseTicketsDto {

    private String id;
    private LocalDateTime createdAt;
    private String stageName;
    private String stageType;
    private LocalDateTime date;
    private String place;
    private String status;


    public ResponseTicketsDto(Ticket ticket){
        this.id= String.valueOf(ticket.getId());
        this.createdAt = ticket.getCreatedAt();
        this.date = ticket.getDate();
        this.status = ticket.getStatus().name();
        this.stageType = ticket.getStageType();
        this.stageName = ticket.getStageName();
        this.place = ticket.getPlace();
    }
}
