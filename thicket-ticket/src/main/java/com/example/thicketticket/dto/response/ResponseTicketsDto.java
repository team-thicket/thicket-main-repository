package com.example.thicketticket.dto.response;

import com.example.thicketticket.domain.Ticket;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ResponseTicketsDto {

    @NotBlank
    private String ticketNumber;

    @NotBlank
    private String stageName;

    @NotNull
    private LocalDateTime date;

    @NotBlank
    private String chairType;

    @NotBlank
    private String memberName;

    @NotNull
    private Long memberId;

    // Ticket 엔티티를 ResponseAllTicketsDto로 변환하는 메소드
    public static ResponseTicketsDto toDto(Ticket ticket) {
        ResponseTicketsDto responseTicketsDto = new ResponseTicketsDto();
        responseTicketsDto.setStageName(ticket.getStageName());
        responseTicketsDto.setDate(ticket.getDate());
        responseTicketsDto.setChairType(ticket.getChairType());
        responseTicketsDto.setMemberName(ticket.getMemberName());
        responseTicketsDto.setMemberId(ticket.getMemberId());
        return responseTicketsDto;
    }
}
