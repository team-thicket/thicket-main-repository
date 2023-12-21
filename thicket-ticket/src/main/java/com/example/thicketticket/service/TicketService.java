package com.example.thicketticket.service;

import com.example.thicketticket.dto.request.RequestCreateTicketDto;
import com.example.thicketticket.dto.response.ResponseAdminTicketDto;
import com.example.thicketticket.dto.response.ResponseTicketDto;
import com.example.thicketticket.dto.response.ResponseTicketsByStageIdDto;
import com.example.thicketticket.dto.response.ResponseTicketsDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;

import java.util.concurrent.CompletableFuture;
import java.util.List;
import java.util.UUID;


public interface TicketService {

    //티켓 예매

    CompletableFuture<String> createTicket(RequestCreateTicketDto ticketDto);

    //admin 티켓id로 조회
    ResponseAdminTicketDto adminFindById(UUID id);
    // 티켓 상세 조회
    ResponseTicketDto findById(UUID id);

    //admin 공연별 티켓조회
    Page<ResponseTicketsByStageIdDto> findByStageId(String stageId, Pageable pageable);
    Page<ResponseTicketsDto> findByMemberIdAndDateBefore(String memberId, LocalDateTime currentTime, Pageable pageable);
    Page<ResponseTicketsDto> findByMemberIdAndDateAfter(String memberId, LocalDateTime currentTime, Pageable pageable);
    // 삭제
    void deleteTicket(UUID id);
}
