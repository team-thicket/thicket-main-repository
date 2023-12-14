package com.example.thicketticket.controller;

import com.example.thicketticket.dto.request.RequestCreateTicketDto;
import com.example.thicketticket.dto.request.RequestDeleteTicketDto;
import com.example.thicketticket.dto.response.ResponseTicketsDto;
import com.example.thicketticket.dto.response.ResponseTicketDto;
import com.example.thicketticket.service.TicketService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("tickets")
@RequiredArgsConstructor
@Tag(name = "Controller", description = "API")
public class TicketController {


    private final TicketService ticketService;

    //티켓 예매id
    @PostMapping("")
    public ResponseEntity<?> createTicket(@RequestBody @Valid RequestCreateTicketDto ticketDto) {
        RequestCreateTicketDto createTicketDto = ticketService.createTicket(ticketDto);

        return new ResponseEntity<>(createTicketDto,HttpStatus.CREATED);
    }

    //티켓
    @GetMapping("/{ticketNumber}")
    public ResponseEntity<ResponseTicketDto> getTicketByUuid(@PathVariable String ticketNumber) {
        ResponseTicketDto resTicketDto = ticketService.findTicketByTicketNumber(ticketNumber);
        return ResponseEntity.ok(resTicketDto);
    }

    @GetMapping("/member/{memberId}")
    public ResponseEntity<List<ResponseTicketsDto>> getTicketsByMemberId(
        @PathVariable Long memberId) {

        List<ResponseTicketsDto> getTicketsDto = ticketService.getAllTicketsByMemberId(memberId);
        return ResponseEntity.ok(getTicketsDto);
    }

    @DeleteMapping("/{ticketNumber}")
    public ResponseEntity<String> deleteTicket(@PathVariable String ticketNumber) {
        ticketService.deleteTicket(ticketNumber);
        return ResponseEntity.status(HttpStatus.OK).body("티켓 취소");
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> exceptionHandler(Exception e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> bindingHandler(BindingResult bindingResult) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        .body(bindingResult.getFieldErrors().get(0).getDefaultMessage());
    }
}
