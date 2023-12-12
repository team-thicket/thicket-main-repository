package com.example.thicketstage.controller;

import com.example.thicketstage.dto.request.RequestCreateStageDto;
import com.example.thicketstage.dto.request.RequestSetNewStatusDto;
import com.example.thicketstage.dto.request.RequestUpdateInfoDto;
import com.example.thicketstage.dto.response.ResponseStageThumbnailDto;
import com.example.thicketstage.enumerate.StageStatus;
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

    @PostMapping("") // api 명세 => POST /stages
    public ResponseEntity<?> createStage(@RequestBody @Valid RequestCreateStageDto stageDto) {
        RequestCreateStageDto createStageDto = stageService.createStage(stageDto);

        return new ResponseEntity<>(createStageDto, HttpStatus.CREATED);
    }

    @GetMapping("all") // api 명세 => GET /stages/all - memberuuid 엮어서 내가 쓴 글 all이 필요
    public ResponseEntity<?> getAllStages() {
        return ResponseEntity.ok(stageService.getAllStage());
    }

    // STATUS.ONGOING 인 공연 모두 최신 순으로 ->작성중
//    @GetMapping("ongoing") // api 명세 => GET /ongoing
//    public ResponseEntity<?> getOngoingList(@RequestParam(defaultValue = "0") int page,
//                                           @RequestParam(defaultValue = "6") int size) {
//        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
//        Page<ResponseStageThumbnailDto> ongoingList = stageService.getOngoingList(StageStatus.ONGOING,
//                                                                                pageable);
//        return ResponseEntity.ok(ongoingList.getContent());
//    }

    // 공연 하나 선택 했을 때 상세 페이지 조회 되게
    @GetMapping("stageDetail/{uuid}") // api 명세 => GET /stages/stageDetail/{id}
    public ResponseEntity<?> getStageDetail(@PathVariable @Valid String uuid) {
        return ResponseEntity.ok(stageService.stageDetail(uuid));
    }

    // StageType별로 줄세우기 -> ONGOING + 최신순 수정해야함
    @GetMapping("all/stagetype/{stagetype}") // api 명세 => GET /stages/all/{stagetype}
    public ResponseEntity<?> getStageTypeList(@PathVariable("stagetype")
                                              @Valid StageType stageType){
        List<ResponseStageThumbnailDto> stageTypeList = stageService.getStageTypeList(stageType);

        return new ResponseEntity<>(stageTypeList, HttpStatus.OK);
    }

    // StageStatus별로 줄 세우기 -> ONGOING과 BEFORE로 나누기
    @GetMapping("all/{stagestatus}") // api 명세 => GET /stages/all/{stagestatus}
    public ResponseEntity<?> getStageStatusList(@PathVariable("stagestatus")
                                                @Valid StageStatus stageStatus) {
        List<ResponseStageThumbnailDto> stageStatusList = stageService.getStageStatusList(stageStatus);

        return new ResponseEntity<>(stageStatusList, HttpStatus.OK);
    }

    // keyword로 검색
    @GetMapping("search/{keyword}") // api 명세 => GET /stages/search/{keyword}
    public ResponseEntity<?> searchStage(@PathVariable @Valid String keyword) {
        List<ResponseStageThumbnailDto> stageThumbnailDtos = stageService.searchStage(keyword);

        return new ResponseEntity<>(stageThumbnailDtos, HttpStatus.OK);
    }

    @PatchMapping("update/{uuid}") // api 명세 => PATCH /stages/update/{uuid}
    public ResponseEntity<?> updateInfo(@PathVariable String uuid,
                                        @RequestBody @Valid RequestUpdateInfoDto updateInfoDto) {
        stageService.updateInfo(uuid, updateInfoDto);

        return ResponseEntity.ok("수정이 완료되었습니다.");
    }

    @PatchMapping("changeStatus/{uuid}") // api 명세 => PATCH /stages/changeStatus/{id}
    public ResponseEntity<?> changeStatus(@PathVariable String uuid,
                                          @RequestBody @Valid RequestSetNewStatusDto setNewStatusDto) {
        stageService.changeStatus(uuid, setNewStatusDto);

        return ResponseEntity.ok("수정이 완료되었습니다.");
    }

    @DeleteMapping("{uuid}") // api 명세 => DELETE /stages/{uuid}
    public ResponseEntity<?> deleteStage(@PathVariable @Valid String uuid) {
        stageService.deleteStage(uuid);

        return ResponseEntity.ok("삭제가 완료되었습니다.");
    }

    // 예외 처리
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> exceptionHandler(Exception e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
}
