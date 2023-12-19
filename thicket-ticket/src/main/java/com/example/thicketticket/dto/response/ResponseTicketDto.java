package com.example.thicketticket.dto.response;

import com.example.thicketticket.domain.Ticket;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@NoArgsConstructor
public class ResponseTicketDto {

    private String id;

    private LocalDateTime createdAt;

    private LocalDateTime date;

    private LocalDateTime cancelDate;

    private String stageName;

    private String stageType;

    private String place;

    private int price;

    private String chairType;

    private int count;

    private String memberName;

    private String howReceive;

    private String method;

    private String status;

    private Long stageId;

    private Long memberId;


    // Ticket 엔티티를 ResponseAllTicketsDto로 변환하는 메소드
    public ResponseTicketDto(Ticket ticket) {
        this.id = String.valueOf(ticket.getId());
        this.createdAt = ticket.getCreatedAt();
        this.date = ticket.getDate();
        this.cancelDate = ticket.getCancelDate();
        this.stageName = ticket.getStageName();
        this.stageType = ticket.getStageType();
        this.place = ticket.getPlace();
        this.chairType = ticket.getChairType();
        this.count = ticket.getCount();
        this.price = ticket.getPrice();
        this.memberName = ticket.getMemberName();
        this.howReceive = ticket.getPayment().getHowReceive().name();
        this.method = ticket.getPayment().getMethod().name();
        this.status = String.valueOf(ticket.getStatus());

    }

}
