package com.example.thicketstage.controller;

import com.example.thicketstage.dto.request.RequestCreateStageStartDto;
import com.example.thicketstage.service.StageStartService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("tickets")
@RequiredArgsConstructor
public class StageStartController {
    private final StageStartService stageStartService;

    @PostMapping("") // api 명세 => POST /stageStarts
    public ResponseEntity<?> createStageStart(@RequestBody
                                              @Valid RequestCreateStageStartDto stageStartDto) {
        stageStartService.createStageStart(stageStartDto);

        return new ResponseEntity<>("공연 일자 등록 성공!!", HttpStatus.CREATED);
    }

    // stageuuid 받아와서 stage별 stageStart all list 뽑아주는 메서드로 수정
    @GetMapping("all") // api 명세 => GET /stageStarts/all
    public ResponseEntity<?> getAllDate() {
        return ResponseEntity.ok(stageStartService.getAllDate());
    }

    // 회차 정보 수정은 추후 고도화 구현시 구현 예정
//    @PatchMapping("update/{id}")
//    public ResponseEntity<?> updateStageStart(@PathVariable Long id,
//                                              @RequestBody @Valid RequestStageStartUpdateDto updateDto) {
//        stageStartService.updateStageStart(id, updateDto);
//
//        return ResponseEntity.ok("수정이 완료되었습니다.");
//    }

    // 추후 고도화 구현시 구현 예정
//    @DeleteMapping("{uuid}") // api 명세 => DELETE /stageStarts/{uuid}
//    public ResponseEntity<?> deleteStageStart(@PathVariable @Valid String uuid) {
//        stageStartService.deleteStageStart(uuid);
//
//        return ResponseEntity.ok("삭제가 완료되었습니다.");
//    }

    // 예외 처리
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> exceptionHandler(Exception e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
}
