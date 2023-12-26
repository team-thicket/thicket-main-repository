package com.example.thicketticket.repository;

import com.example.thicketticket.domain.Ticket;
import com.example.thicketticket.dto.request.RequestCreateTicketDto;
import com.example.thicketticket.dto.response.ResponseTicketsDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TicketRepository extends JpaRepository<Ticket, UUID> {



        //티켓
        // 티켓 조회 시 Payment를 조인하여 가져오기
        @Query("SELECT t FROM Ticket t LEFT JOIN FETCH t.payment WHERE t.id = :id AND t.deleted = false")
        Optional<Ticket> findByIdAndDeletedFalse(@Param("id") UUID id);

        //티켓 전체조회 by memberId
        Page<Ticket> findByMemberIdAndDateBeforeAndDeletedFalse(UUID memberId, LocalDateTime currentTime, Pageable pageable);
        Page<Ticket> findByMemberIdAndDateAfterAndDeletedFalse(UUID memberId,LocalDateTime currentTime, Pageable pageable);
        //admin 공연별 예매티켓 조회
        Page<Ticket> findByStageIdAndDeletedFalse(UUID stageId,Pageable pageable);
}
