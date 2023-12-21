package com.example.thicketticket.service;

import com.example.thicketticket.domain.Payment;
import com.example.thicketticket.domain.Ticket;
import com.example.thicketticket.dto.request.RequestCreateTicketDto;
import com.example.thicketticket.dto.response.ResponseAdminTicketDto;
import com.example.thicketticket.dto.response.ResponseTicketDto;
import com.example.thicketticket.dto.response.ResponseTicketsByStageIdDto;
import com.example.thicketticket.dto.response.ResponseTicketsDto;
import com.example.thicketticket.repository.TicketRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Transactional
class TicketServiceImplTest {

    @Autowired
    TicketRepository ticketRepository;
    @Autowired
    TicketService ticketService;



    //티켓 취소
    @Test
    @Transactional
    void deleteTicket() {
        // given
        Ticket ticket = Ticket.createTicket(
                "Example Stage",
                "Example Place",
                LocalDateTime.of(2023, 12, 31, 19, 30), // 예약 날짜 및 시간
                "VIP",
                2,
                "John Doe",
                "123-4567-8901",
                10000,
                LocalDateTime.of(2023, 12, 31, 19, 30),// 취소 날짜 (취소되지 않은 경우 null)
                "1L", // 더미 스테이지 ID
                "1L", // 더미 멤버 ID
                "Concert" // 더미 스테이지 타입

        );
        ticketRepository.save(ticket);
        UUID id = ticket.getId();

        // when
        ticketService.deleteTicket(id);

        // then
        Ticket deletedTicket = ticketRepository.findById(id).orElse(null);
        assertTrue(deletedTicket.isDeleted()); // 실행 후 deleted는 true
    }


    @Test
    @Transactional
    void findById() {
        //given
        Ticket ticket2 = Ticket.createTicket(
                "Example Stage",
                "Example Place",
                LocalDateTime.of(2023, 12, 31, 19, 30), // 예약 날짜 및 시간
                "VIP",
                2,
                "John Doe",
                "123-4567-8901",
                10000,
                LocalDateTime.of(2023, 12, 31, 19, 30),// 취소 날짜 (취소되지 않은 경우 null)
                "1L", // 더미 스테이지 ID
                "1L", // 더미 멤버 ID
                "Concert" // 더미 스테이지 타입

        );
        Ticket savedTicket = ticketRepository.save(ticket2);
        Payment newPayment = Payment.createPayment(savedTicket.getMemberId(), String.valueOf(savedTicket.getId()),savedTicket.getStageId());
        savedTicket.setPayment(newPayment); // Payment 설정

       UUID id = ticket2.getId();
        //when
        ResponseTicketDto responseTicketDto = ticketService.findById(id);


        //then
        assertEquals("Example Stage", responseTicketDto.getStageName());
        assertEquals("Example Place", responseTicketDto.getPlace());
        assertEquals(LocalDateTime.of(2023, 12, 31, 19, 30), responseTicketDto.getDate());
        assertEquals("VIP", responseTicketDto.getChairType());
        assertEquals(2, responseTicketDto.getCount());
        assertEquals("John Doe", responseTicketDto.getMemberName());
        assertEquals(10000, responseTicketDto.getPrice());
        assertEquals("Example Place", responseTicketDto.getPlace());
        assertEquals(LocalDateTime.of(2023, 12, 31, 19, 30), responseTicketDto.getCancelDate());
    }

    @Test
    @Transactional
    void adminFindById() {
        //given
        Ticket ticket3 = Ticket.createTicket(
                "Example Stage",
                "Example Place",
                LocalDateTime.of(2023, 12, 31, 19, 30), // 예약 날짜 및 시간
                "VIP",
                2,
                "John Doe",
                "123-4567-8901",
                10000,
                LocalDateTime.of(2023, 12, 31, 19, 30),// 취소 날짜 (취소되지 않은 경우 null)
                "1L", // 더미 스테이지 ID
                "1L", // 더미 멤버 ID
                "Concert" // 더미 스테이지 타입

        );
        ticketRepository.save(ticket3);
        UUID id = ticket3.getId();
        //when
        ResponseAdminTicketDto responseAdminTicketDto = ticketService.adminFindById(id);

        //then
        assertEquals(String.valueOf(id), responseAdminTicketDto.getId());
        assertEquals("Example Stage", responseAdminTicketDto.getStageName());
        assertEquals("Example Place", responseAdminTicketDto.getPlace());
        assertEquals(LocalDateTime.of(2023, 12, 31, 19, 30), responseAdminTicketDto.getDate());
        assertEquals("Concert", responseAdminTicketDto.getStageType());
        assertEquals("RESERVE", responseAdminTicketDto.getStatus());
    }

    @Test
    @Transactional
    void findByStageId() {
        //given
        Ticket ticket5 = Ticket.createTicket(
                "Example Stage",
                "Example Place",
                LocalDateTime.of(2023, 12, 31, 19, 30), // 예약 날짜 및 시간
                "VIP",
                2,
                "John Doe",
                "123-4567-8901",
                10000,
                LocalDateTime.of(2023, 12, 31, 19, 30),// 취소 날짜 (취소되지 않은 경우 null)
                "2L", // 더미 스테이지 ID
                "1L", // 더미 멤버 ID
                "Concert" // 더미 스테이지 타입

        );
        ticketRepository.save(ticket5);

        //given
        Ticket ticket6 = Ticket.createTicket(
                "Example Stage",
                "Example Place",
                LocalDateTime.of(2023, 12, 31, 19, 30), // 예약 날짜 및 시간
                "VIP",
                2,
                "John Doe2",
                "123-4567-8901",
                10000,
                LocalDateTime.of(2023, 12, 31, 19, 30),// 취소 날짜 (취소되지 않은 경우 null)
                "10L", // 더미 스테이지 ID
                "1L", // 더미 멤버 ID
                "Concert" // 더미 스테이지 타입

        );
        ticketRepository.save(ticket6);
        String StageId = ticket6.getStageId();
        //when
        Pageable pageable = PageRequest.of(0, 10, Sort.by("date").ascending());
        Page<ResponseTicketsByStageIdDto> responseTicketsByStageIdDto = ticketService.findByStageId(StageId,pageable);

        //then
        assertEquals(1, responseTicketsByStageIdDto.getTotalElements());
        assertTrue(responseTicketsByStageIdDto.stream().allMatch(dto -> dto.getMemberName().equals("John Doe2")));

    }

    @Test
    void findByMemberIdAndDateBefore() {
        //given
        Ticket ticket7 = Ticket.createTicket(
                "Example Stage",
                "Example Place",
                LocalDateTime.of(2023, 12, 31, 19, 30), // 예약 날짜 및 시간
                "VIP",
                2,
                "John Doe",
                "123-4567-8901",
                10000,
                LocalDateTime.of(2023, 12, 31, 19, 30),// 취소 날짜 (취소되지 않은 경우 null)
                "2L", // 더미 스테이지 ID
                "1L", // 더미 멤버 ID
                "Concert" // 더미 스테이지 타입

        );
        ticketRepository.save(ticket7);

        //given
        Ticket ticket8 = Ticket.createTicket(
                "Example Stage2",
                "Example Place",
                LocalDateTime.of(2023, 11, 11, 19, 30), // 예약 날짜 및 시간
                "VIP",
                2,
                "John Doe2",
                "123-4567-8901",
                10000,
                LocalDateTime.of(2023, 12, 31, 19, 30),// 취소 날짜 (취소되지 않은 경우 null)
                "1L", // 더미 스테이지 ID
                "20L", // 더미 멤버 ID
                "Concert" // 더미 스테이지 타입

        );
        ticketRepository.save(ticket8);
        String MemberId = ticket8.getMemberId();
        LocalDateTime currentTime = LocalDateTime.now();
        //when
        Pageable pageable = PageRequest.of(0, 10, Sort.by("date").ascending());
        Page<ResponseTicketsDto> responseTicketsDtos = ticketService.findByMemberIdAndDateBefore(MemberId, currentTime,pageable );
        //then
        assertEquals(1, responseTicketsDtos.getTotalElements());
        assertTrue(responseTicketsDtos.stream().allMatch(dto -> dto.getStageName().equals("Example Stage2")));
    }

    @Test
    void findByMemberIdAndDateAfter() {
        //given
        Ticket ticket9 = Ticket.createTicket(
                "Example Stage",
                "Example Place",
                LocalDateTime.of(2023, 12, 31, 19, 30), // 예약 날짜 및 시간
                "VIP",
                2,
                "John Doe",
                "123-4567-8901",
                10000,
                LocalDateTime.of(2023, 12, 31, 19, 30),// 취소 날짜 (취소되지 않은 경우 null)
                "2L", // 더미 스테이지 ID
                "122L", // 더미 멤버 ID
                "Concert" // 더미 스테이지 타입

        );
        ticketRepository.save(ticket9);

        //given
        Ticket ticket11 = Ticket.createTicket(
                "Example Stage2",
                "Example Place",
                LocalDateTime.of(2023, 11, 11, 19, 30), // 예약 날짜 및 시간
                "VIP",
                2,
                "John Doe2",
                "123-4567-8901",
                10000,
                LocalDateTime.of(2023, 12, 31, 19, 30),// 취소 날짜 (취소되지 않은 경우 null)
                "1L", // 더미 스테이지 ID
                "123L", // 더미 멤버 ID
                "Concert" // 더미 스테이지 타입

        );
        ticketRepository.save(ticket11);
        String MemberId = ticket9.getMemberId();
        LocalDateTime currentTime = LocalDateTime.now();
        //when
        Pageable pageable = PageRequest.of(0, 10, Sort.by("date").ascending());
        Page<ResponseTicketsDto> responseTicketsDtos2 = ticketService.findByMemberIdAndDateAfter(MemberId, currentTime,pageable );

        //then
        assertEquals(1, responseTicketsDtos2.getTotalElements());
        assertTrue(responseTicketsDtos2.stream().allMatch(dto -> dto.getStageName().equals("Example Stage")));
    }
}