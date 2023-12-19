package com.example.thicketticket.dto.response;

import com.example.thicketticket.domain.Ticket;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class ResponseAdminTicketDto {
    private String id;
    private LocalDateTime createdAt;
    private String stageName;
    private String stageType;
    private LocalDateTime date;
    private String place;
    private String status;


    public ResponseAdminTicketDto(Ticket ticket){
        this.id= String.valueOf(ticket.getId());
        this.createdAt = ticket.getCreatedAt();
        this.date = ticket.getDate();
        this.stageType = ticket.getStageType();
        this.status = String.valueOf(ticket.getStatus());
        this.stageName = ticket.getStageName();
        this.place = ticket.getPlace();
    }
}
