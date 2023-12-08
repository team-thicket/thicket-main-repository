package com.example.thicketticket.service;

import com.example.thicketticket.domain.Ticket;
import com.example.thicketticket.dto.request.RequestCreateTicketDto;
import com.example.thicketticket.dto.request.RequestDeleteTicketDto;
import com.example.thicketticket.dto.response.ResponseTicketsDto;
import com.example.thicketticket.dto.response.ResponseTicketDto;
import com.example.thicketticket.repository.TicketRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService{

    private final TicketRepository ticketRepository;
    @PersistenceContext
    private EntityManager entityManager;


    //생성
    @Override
    @Transactional
    public void createTicket(RequestCreateTicketDto dto) {
        Ticket ticket = dto.toEntity();
        ticketRepository.save(ticket);
    }
    //단일 티켓 조회
    @Override
    @Transactional
    public ResponseTicketDto findTicketByTicketNumber(String ticketNumber) {
        Ticket findTicket = ticketRepository.findTicketByUuid(ticketNumber);

        if (findTicket == null) {
            throw new IllegalArgumentException("존재하지 않는 티켓.");
        }

        return ResponseTicketDto.toDto(findTicket);
    }
    //사용자의 전체 티켓 조회
    @Override
    @Transactional
    public List<ResponseTicketsDto> getAllTicketsByMemberId(Long memberId) {
        List<Ticket> allTickets = ticketRepository.findAllByMemberIdAndDeletedFalse(memberId);
        return allTickets.stream()
                .map(ResponseTicketsDto::toDto)
                .collect(Collectors.toList());
    }

    //티켓 삭제
    @Override
    @Transactional
    public void deleteTicket(String ticketNumber) {
        Ticket findTicket = ticketRepository.findTicketByUuid(ticketNumber);

        if (findTicket == null) {
            throw new IllegalArgumentException("존재하지 않는 티켓.");
        }

        findTicket.updateDeleted(true);

    }
}
