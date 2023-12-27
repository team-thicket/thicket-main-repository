package com.example.thicketloader.service;

import com.example.thicketloader.domain.Member;
import com.example.thicketloader.domain.Ticket;
import com.example.thicketloader.dto.TicketDto;
import com.example.thicketloader.repository.MemberRepository;
import com.example.thicketloader.repository.TicketRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;
import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class TicketService {
    private final TicketRepository ticketRepository;
    private final MemberRepository memberRepository;

    @Transactional(readOnly = true)
    public String findName(UUID memberId) {
        return findMemberField(memberId, Member::getName);
    }

    @Transactional(readOnly = true)
    public String findPhone(UUID memberId) {
        return findMemberField(memberId, Member::getPhone);
    }

    @Transactional
    public void save(TicketDto ticketDto) {
        Ticket ticket = ticketDto.toEntity();
        ticketRepository.save(ticket);
    }

    private String findMemberField(UUID memberId, Function<Member, String> fieldExtractor) {
        return memberRepository.findById(memberId)
                .map(fieldExtractor)
                .orElseThrow(() -> new EntityNotFoundException("Member not found with ID: " + memberId));
    }
}