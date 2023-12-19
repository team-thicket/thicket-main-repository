package com.example.thicketticket.service;

import com.example.thicketticket.domain.Payment;
import com.example.thicketticket.domain.Ticket;
import com.example.thicketticket.dto.request.RequestCreateTicketDto;
import com.example.thicketticket.dto.response.ResponseAdminTicketDto;
import com.example.thicketticket.dto.response.ResponseTicketDto;
import com.example.thicketticket.dto.response.ResponseTicketsByStageIdDto;
import com.example.thicketticket.dto.response.ResponseTicketsDto;
import com.example.thicketticket.enumerate.Status;
import com.example.thicketticket.repository.TicketRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService{

    private final TicketRepository ticketRepository;

    //생성
    @Override
    @Transactional
    public RequestCreateTicketDto createTicket(RequestCreateTicketDto ticketDto) {
        Ticket ticket = ticketDto.toEntity();
        // 엔터티를 저장하고 저장된 엔터티를 받아옴
        Ticket savedTicket = ticketRepository.save(ticket);

        Payment newPayment = Payment.createPayment(savedTicket.getMemberId(), String.valueOf(savedTicket.getId()),savedTicket.getStageId());
        savedTicket.setPayment(newPayment); // Payment 설정
        // ID 값을 DTO에 설정
        ticketDto.setId(savedTicket.getId());

        // 저장된 DTO 반환
        return ticketDto;
    }
    //단일 티켓 조회
    @Override
    @Transactional
    public ResponseTicketDto findById(UUID id) {
        Optional<Ticket> findTicket = ticketRepository.findByIdAndDeletedFalse(id);

        if (findTicket.isEmpty()) {
            throw new IllegalArgumentException("존재하지 않는 티켓.");
        }
        Ticket ticket = findTicket.get();
        return new ResponseTicketDto(ticket);
    }

    //admin 티켓 id 조회
    @Override
    @Transactional
    public ResponseAdminTicketDto adminFindById(UUID id) {
        Optional<Ticket> findTicket = ticketRepository.findByIdAndDeletedFalse(id);

        if (findTicket.isEmpty()) {
            throw new IllegalArgumentException("존재하지 않는 티켓.");
        }

        Ticket ticket = findTicket.get();

        return new ResponseAdminTicketDto(ticket);
    }
    //사용자가 사용한 티켓 조회
    @Override
    @Transactional
    public Page<ResponseTicketsDto> findByMemberIdAndDateBefore(String memberId, LocalDateTime currentTime, Pageable pageable) {
        Page<Ticket> tickets = ticketRepository.findByMemberIdAndDateBeforeAndDeletedFalse(memberId,currentTime,pageable);
        if (tickets.isEmpty()) {
            throw new EntityNotFoundException("공연의 예매가 존재하지 않습니다.");
        }

        return tickets.map(ResponseTicketsDto::new);
    }
    //사용자가 앞으로 볼 티켓 조회
    @Override
    @Transactional
    public Page<ResponseTicketsDto> findByMemberIdAndDateAfter(String memberId, LocalDateTime currentTime, Pageable pageable) {
        Page<Ticket> ticketsPage = ticketRepository.findByMemberIdAndDateAfterAndDeletedFalse(memberId, currentTime, pageable);

        if (ticketsPage.isEmpty()) {
            throw new EntityNotFoundException("공연의 예매가 존재하지 않습니다.");
        }

        return ticketsPage.map(ResponseTicketsDto::new);
    }
    //admin 공연별 티켓조회
    @Override
    @Transactional
    public Page<ResponseTicketsByStageIdDto> findByStageId(String Stage, Pageable pageable) {
        Page<Ticket> tickets = ticketRepository.findByStageIdAndDeletedFalse(Stage, pageable);

        if(tickets.isEmpty()){
            throw new EntityNotFoundException("공연의 예매가 존재하지 않습니다.");
        }
       return tickets.map(ResponseTicketsByStageIdDto::new);

    }

    //티켓 삭제
    @Override
    @Transactional
    public void deleteTicket(UUID id) {
        Optional<Ticket> findTicket = ticketRepository.findById(id);

        if(findTicket.isEmpty()){
            throw new EntityNotFoundException("공연을 찾을 수 없습니다.");
        }

        Ticket ticket = findTicket.get();
        //티켓 STATUS가 RESERVE 일 경우
        if(ticket.getStatus().equals(Status.RESERVE)) {
            ticket.updateDeleted(true);
        }
        //티켓 상태가 PAY일경우
    }
}
