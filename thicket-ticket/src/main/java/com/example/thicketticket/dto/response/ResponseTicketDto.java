package com.example.thicketticket.dto.response;

import com.example.thicketticket.domain.Ticket;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
@Data
public class ResponseTicketDto {

    @NotBlank
    private String ticketNumber;

    @NotBlank
    private String stageName;

    @NotBlank
    private String date;

    @NotBlank
    private String place;

    @NotBlank
    private String chairType;

    @NotBlank
    private String memberName;

    @NotNull
    private int price;

    @NotBlank
    private Long stageId;

    @NotBlank
    private Long memberId;


    // Ticket 엔티티를 ResponseAllTicketsDto로 변환하는 메소드
    public static ResponseTicketDto toDto(Ticket ticket) {
        ResponseTicketDto responseTicketDto = new ResponseTicketDto();

        responseTicketDto.setStageName(ticket.getStageName());
        responseTicketDto.setDate(ticket.getDate());
        responseTicketDto.setPlace(ticket.getPlace());
        responseTicketDto.setChairType(ticket.getChairType());
        responseTicketDto.setMemberName(ticket.getMemberName());
        responseTicketDto.setPrice(ticket.getPrice());
        responseTicketDto.setStageId(ticket.getStageId());
        responseTicketDto.setMemberId(ticket.getMemberId());
        return responseTicketDto;
    }
}
