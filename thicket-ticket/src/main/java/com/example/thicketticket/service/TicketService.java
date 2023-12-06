package com.example.thicketticket.service;

import com.example.thicketticket.dto.request.RequestCreateTicketDto;
import com.example.thicketticket.dto.request.RequestDeleteTicketDto;
import com.example.thicketticket.dto.response.ResponseTicketsDto;
import com.example.thicketticket.dto.response.ResponseTicketDto;

import java.util.List;

public interface TicketService {

    //티켓 예매
    void createTicket(RequestCreateTicketDto dto);

    // 티켓 상세 조회
    ResponseTicketDto findTicketByTicketNumber(String ticketNumber);

    // 티켓 전체 조회
    List<ResponseTicketsDto> getAllTicketsByMemberId(Long memberId);

    // 삭제
    void deleteTicket(String ticketNumber);
}
