package com.example.thicketstage.controller;

import com.example.thicketstage.dto.request.RequestCreateChairDto;
import com.example.thicketstage.dto.request.RequestUpdateChairDto;
import com.example.thicketstage.service.ChairService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("chairs")
@RequiredArgsConstructor
public class ChairController {

    private final ChairService chairService;

    @PostMapping("")  // API 명세 => POST /chairs
    public ResponseEntity<?> createChair(@RequestBody @Valid RequestCreateChairDto chairDto){
        chairService.createChair(chairDto);

        return new ResponseEntity<>("좌석 등록 성공!!", HttpStatus.CREATED);
    }

    // 단일 조회
    @GetMapping("chairdetail/{uuid}")  // API 명세 => GET /chairs/chairdetail/{uuid}
    public ResponseEntity<?> getChairDetail(@PathVariable String uuid) {
        return ResponseEntity.ok(chairService.findChairByUuid(uuid));
    }

    @GetMapping("all")  // API 명세 => GET /chairs/all
    public ResponseEntity<?> getAllChair() {
        return ResponseEntity.ok(chairService.getAllChair());
    }

    // 좌석 정보 수정은 추후 고도화시 구현
    @PatchMapping("update/{uuid}")  // API 명세 => PATCH /chairs/update/{uuid}
    public ResponseEntity<?> updateChair(@PathVariable String uuid,
                                         @RequestBody @Valid RequestUpdateChairDto updateDto){
        chairService.updateChair(uuid, updateDto);

        return ResponseEntity.ok("수정이 완료되었습니다.");
    }

    @DeleteMapping("{uuid}")  // API 명세 => DELETE /chairs/{uuid}
    public ResponseEntity<?> deleteChair(@PathVariable @Valid String uuid) {
        chairService.deleteChair(uuid);

        return ResponseEntity.ok("삭제가 완료되었습니다");
    }

    // 예외 처리
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> exceptionHandler(Exception e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
}
