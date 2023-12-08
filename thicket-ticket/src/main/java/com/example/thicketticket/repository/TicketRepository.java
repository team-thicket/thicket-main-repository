package com.example.thicketticket.repository;

import com.example.thicketticket.domain.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Long> {

    //티켓
    Ticket findTicketByUuid(String uuid);

    //티켓 전체조회 by memberId
    List<Ticket> findAllByMemberIdAndDeletedFalse(Long memberId);

}
