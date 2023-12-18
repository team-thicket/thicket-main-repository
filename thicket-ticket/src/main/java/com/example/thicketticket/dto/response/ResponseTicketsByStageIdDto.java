package com.example.thicketticket.dto.response;

import com.example.thicketticket.domain.Ticket;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class ResponseTicketsByStageIdDto {


    private String id;
    private String place;
    private String phone;
    private String memberName;

    public ResponseTicketsByStageIdDto(Ticket ticket){
        this.id= String.valueOf(ticket.getId());
        this.memberName = ticket.getMemberName();
        this.phone = ticket.getPhone();
        this.place = ticket.getPlace();
    }


}
