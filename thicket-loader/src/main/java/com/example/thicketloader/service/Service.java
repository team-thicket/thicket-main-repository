package com.example.thicketloader.service;

import com.example.thicketloader.domain.Ticket;
import com.example.thicketloader.dto.TicketDto;
import com.example.thicketloader.repository.TicketRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class Service {
    private final TicketRepository ticketRepository;

    @Transactional
    public void save(TicketDto ticketDto) {
        Ticket ticket = ticketDto.toEntity();

        ticketRepository.save(ticket);
    }
}
