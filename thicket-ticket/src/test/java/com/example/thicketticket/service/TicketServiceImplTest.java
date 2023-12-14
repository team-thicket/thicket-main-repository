package com.example.thicketticket.service;

import com.example.thicketticket.domain.Ticket;
import com.example.thicketticket.dto.request.RequestCreateTicketDto;
import com.example.thicketticket.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest

class TicketServiceImplTest {
    @Autowired
    TicketRepository ticketRepository;
    @Autowired
    TicketService ticketService;

    @Test
    @Transactional
    void createTicket() {
        //given
        RequestCreateTicketDto createDto = new RequestCreateTicketDto();

        createDto.setStageName("Example Stage");
        createDto.setDate(LocalDateTime.of(2023, 1, 1, 12, 0));
        createDto.setPlace("Example Place");
        createDto.setChairType("Example Chair Type");
        createDto.setCount(2); // 예시 값
        createDto.setMemberName("Example Member");
        createDto.setPrice(100); // 예시 값
        createDto.setSequence(1); // 예시 값
        createDto.setCancelDate(LocalDateTime.of(2023, 1, 1, 12, 0));
        createDto.setStageId(123L); // 예시 값
        createDto.setMemberId(456L); // 예시 값


        //when
        RequestCreateTicketDto createTicketDto = ticketService.createTicket(createDto);

        //then
        Ticket savedTicket = ticketRepository.findById(createTicketDto.getId())
                .orElseThrow(() -> new AssertionError("저장된 예매가 없습니다."));

        assertEquals(createDto.getStageName(), savedTicket.getStageName());
        assertEquals(createDto.getDate(), savedTicket.getDate());
        assertEquals(createDto.getPlace(), savedTicket.getPlace());
        assertEquals(createDto.getChairType(), savedTicket.getChairType());
        assertEquals(createDto.getCount(), savedTicket.getCount());
        assertEquals(createDto.getMemberName(), savedTicket.getMemberName());
        assertEquals(createDto.getPrice(), savedTicket.getPrice());
        assertEquals(createDto.getSequence(), savedTicket.getSequence());
        assertEquals(createDto.getCancelDate(), savedTicket.getCancelDate());
        assertEquals(createDto.getStageId(), savedTicket.getStageId());
        assertEquals(createDto.getMemberId(), savedTicket.getMemberId());
    }
}