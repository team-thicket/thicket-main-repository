package com.example.thicketstage.controller;

import com.example.thicketstage.dto.request.RequestCreateStageStartDto;
import com.example.thicketstage.dto.response.ResponseStageStartDto;
import com.example.thicketstage.service.StageStartService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("tickets")
@RequiredArgsConstructor
public class StageStartController {

    private final StageStartService stageStartService;

    @PostMapping("") // API 명세 => POST /tickets
    public ResponseEntity<?> createStageStart(@RequestBody
                                              @Valid RequestCreateStageStartDto stageStartDto) {
        stageStartService.createStageStart(stageStartDto);

        return new ResponseEntity<>("공연 일자 등록 성공!!", HttpStatus.CREATED);
    }

    @GetMapping("all") // API 명세 => GET /tickets/all
    public ResponseEntity<?> getAllDate() {
        List<ResponseStageStartDto> allDate = stageStartService.getAllDate();

        if(allDate.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("회차 정보가 존재하지 않습니다.");
        }

        return ResponseEntity.ok(allDate);
    }

    // 공연 하나의 모든 회차정보 조회
    @GetMapping("all/{stageid}") // API 명세 => GET /tickets/all/{stageid}
    public ResponseEntity<?> getStageAllDate(@PathVariable("stageid") UUID stageId) {
        List<ResponseStageStartDto> allStageStart = stageStartService.getStageAllStageStart(stageId);

        if(allStageStart.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("회차 정보가 존재하지 않습니다.");
        }

        return ResponseEntity.ok(allStageStart);
    }

    // 회차 정보 수정은 추후 고도화 구현시 구현 예정
//    @PatchMapping("update/{id}")
//    public ResponseEntity<?> updateStageStart(@PathVariable Long id,
//                                              @RequestBody @Valid RequestStageStartUpdateDto updateDto) {
//        stageStartService.updateStageStart(id, updateDto);
//
//        return ResponseEntity.ok("수정이 완료되었습니다.");
//    }

    // 회차 삭제
    @DeleteMapping("{id}") // API 명세 => DELETE /tickets/{id}
    public ResponseEntity<?> deleteStageStart(@PathVariable @Valid UUID id) {
        stageStartService.deleteStageStart(id);

        return ResponseEntity.ok("삭제가 완료되었습니다.");
    }
}
