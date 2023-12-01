package com.example.thicketstage.controller;

import com.example.thicketstage.dto.request.RequestCreateStageDto;
import com.example.thicketstage.dto.request.RequestSetNewStatusDto;
import com.example.thicketstage.dto.request.RequestUpdateInfoDto;
import com.example.thicketstage.dto.response.ResponseStageDto;
import com.example.thicketstage.dto.response.ResponseStageThumbnailDto;
import com.example.thicketstage.enumerate.StageType;
import com.example.thicketstage.service.StageService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("stages")
@RequiredArgsConstructor
public class StageController {
    private final StageService stageService;

    @PostMapping("")
    public ResponseEntity<?> createStage(@RequestBody @Valid RequestCreateStageDto stageDto) {
        RequestCreateStageDto createStageDto = stageService.createStage(stageDto);

        return new ResponseEntity<>(createStageDto, HttpStatus.CREATED);
    }

    @GetMapping("all")
    public ResponseEntity<?> getAllStages() {
        return ResponseEntity.ok(stageService.getAllStage());
    }

    // 공연 하나 선택 했을 때 상세 페이지 조회 되게
    @GetMapping("stageDetail/{id}")
    public ResponseEntity<?> getStageDetail(@PathVariable @Valid Long id) {
        return ResponseEntity.ok(stageService.stageDetail(id));
    }

    // StageType별로 줄세우기
    @GetMapping("all/{stagetype}")
    public ResponseEntity<?> getStageTypeList(@PathVariable("stagetype") @Valid StageType stageType){
        List<ResponseStageThumbnailDto> stageTypeList = stageService.getStageTypeList(stageType);

        return new ResponseEntity<>(stageTypeList, HttpStatus.OK);
    }

    // keyword로 검색
    @GetMapping("search/{keyword}")
    public ResponseEntity<?> searchStage(@PathVariable @Valid String keyword) {
        List<ResponseStageThumbnailDto> stageThumbnailDtos = stageService.searchStage(keyword);

        return new ResponseEntity<>(stageThumbnailDtos, HttpStatus.OK);
    }

    @PatchMapping("update/{id}")
    public ResponseEntity<?> updateInfo(@PathVariable Long id,
                                        @RequestBody @Valid RequestUpdateInfoDto updateInfoDto) {
        stageService.updateInfo(id, updateInfoDto);

        return ResponseEntity.ok("수정이 완료되었습니다.");
    }

    @PatchMapping("changeStatus/{id}")
    public ResponseEntity<?> changeStatus(@PathVariable Long id,
                                          @RequestBody @Valid RequestSetNewStatusDto setNewStatusDto) {
        stageService.changeStatus(id, setNewStatusDto);

        return ResponseEntity.ok("수정이 완료되었습니다.");
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteStage(@PathVariable @Valid Long id) {
        stageService.deleteStage(id);

        return ResponseEntity.ok("");
    }

    // 예외 처리
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> exceptionHandler(Exception e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
}
