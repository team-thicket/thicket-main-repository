package com.example.thicketticket.controller;

import com.example.thicketticket.dto.request.RequestCreateTicketDto;
import com.example.thicketticket.dto.response.ResponseAdminTicketDto;
import com.example.thicketticket.dto.response.ResponseTicketDto;
import com.example.thicketticket.dto.response.ResponseTicketsByStageIdDto;
import com.example.thicketticket.dto.response.ResponseTicketsDto;
import com.example.thicketticket.service.TicketService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.UUID;

@RestController
@RequestMapping("reservations")
@RequiredArgsConstructor
@Tag(name = "Controller", description = "API")
public class TicketController {

    private final TicketService ticketService;

    //티켓 예매완료 결과 리턴
    @PostMapping("/ticket")
    public ResponseEntity<?> createTicket(@RequestBody @Valid RequestCreateTicketDto ticketDto) {
        RequestCreateTicketDto createTicketDto = ticketService.createTicket(ticketDto);

        return new ResponseEntity<>(createTicketDto,HttpStatus.CREATED);
    }

    //티켓 대기번호 리턴 로직
    @PostMapping("/")
    public String createReserve(){
        //예매티켓의 유니크한 id값생성
        //UUID 로 생성된 id 그대로 사용할 것인지? , 사전에 생성되는 UUID 값으로 할것인지?

        //시간 보정로직

        //카프카에 넣을 메세지 생성
        //카프카에 전송

        //몇번째 순위인지, 결과 성공 실패 여부

        return "예매티켓의 유니크한 아이디 값";
    }

    //예매 정보 조회
    @GetMapping("/{id}")
    public ResponseEntity<ResponseTicketDto> findById(@PathVariable(name = "id") @Valid String id) {
        return ResponseEntity.ok(ticketService.findById(UUID.fromString(id)));
    }

    //ADMIN 요청,티켓 UUID로 특정 예매 조회
    @GetMapping("/admin/{id}")
    public ResponseEntity<ResponseAdminTicketDto> adminFindById(@PathVariable(name = "id") @Valid String id) {
        return ResponseEntity.ok(ticketService.adminFindById(UUID.fromString(id)));
    }

    //사용자 이미 본 공연 예매 조회
    @GetMapping("/past")
    public ResponseEntity<Page<ResponseTicketsDto>> findByMemberIdAndDateAtBefore(
            @RequestHeader(name = "memberId") String memberId,
            Pageable pageable) {


        Page<ResponseTicketsDto> getTicketsDtos = ticketService
                .findByMemberIdAndDateBefore(memberId, LocalDateTime.now(),pageable);
        return new ResponseEntity<>(getTicketsDtos, HttpStatus.OK);
    }
    //사용자 아직 안본 공연 예매 조회
    @GetMapping("/future")
    public ResponseEntity<Page<ResponseTicketsDto>> findByMemberIdAndDateAtAfter(
            @RequestHeader(name = "memberId") String memberId,
            Pageable pageable) {
        Page<ResponseTicketsDto> getTicketsDtos = ticketService
                .findByMemberIdAndDateAfter(memberId, LocalDateTime.now(),pageable);
        return new ResponseEntity<>(getTicketsDtos, HttpStatus.OK);
    }

    //admin 공연별 예매내역 조회
    @GetMapping("/admin/stage/{stageId}")
    public ResponseEntity<Page<ResponseTicketsByStageIdDto>> findByStageId(
            @PathVariable(name="stageId") String stageId,
            Pageable pageable) {
        Page<ResponseTicketsByStageIdDto> getTicketsDos = ticketService.findByStageId(stageId,pageable);
        return new ResponseEntity<>(getTicketsDos, HttpStatus.OK);
    }

    //예매 취소
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTicket(@PathVariable(name = "id") String id) {
        ticketService.deleteTicket(UUID.fromString(id));
        return ResponseEntity.status(HttpStatus.OK).body("티켓 취소");
    }





    //예외 에러코드
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
