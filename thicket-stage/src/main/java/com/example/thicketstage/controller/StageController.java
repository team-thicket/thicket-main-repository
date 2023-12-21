package com.example.thicketstage.controller;

import com.example.thicketstage.dto.request.RequestCreateStageDto;
import com.example.thicketstage.dto.request.RequestUpdateInfoDto;
import com.example.thicketstage.dto.response.ResponseAdminStageDto;
import com.example.thicketstage.dto.response.ResponseStageThumbnailDto;
import com.example.thicketstage.enumerate.StageType;
import com.example.thicketstage.service.StageService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("shows")
@RequiredArgsConstructor
public class StageController {
    private final StageService stageService;

    @PostMapping("") // API 명세 => POST /shows
    public ResponseEntity<?> createStage(@RequestBody @Valid RequestCreateStageDto stageDto) {
        RequestCreateStageDto createStageDto = stageService.createStage(stageDto);

        return new ResponseEntity<>(createStageDto, HttpStatus.CREATED);
    }

    @GetMapping("all") // API 명세 => GET /shows/all - memberuuid 엮어서 관리자 전체 목록이 필요
    public ResponseEntity<?> getAllStages() {
        List<ResponseAdminStageDto> allStage = stageService.getAllStage();

        if(allStage.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("공연 목록이 존재하지 않습니다.");
        }

        return ResponseEntity.ok(allStage);
    }

    // 진행중인 공연 모두 최신 순으로 => main
    @GetMapping("ongoing") // API 명세 => GET /shows/ongoing
    public ResponseEntity<?> getOngoingList(@RequestParam(defaultValue = "0") int page,
                                           @RequestParam(defaultValue = "6") int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
        Page<ResponseStageThumbnailDto> ongoingList = stageService.getOngoingList(pageable);
        return ResponseEntity.ok(ongoingList.getContent());
    }

    // 진행 중인 공연 최신 순으로 => 관리자
    @GetMapping("ongoing/admin") // API 명세 => GET /shows/ongoing
    public ResponseEntity<?> getOngoingListAdmin(@RequestParam(defaultValue = "0") int page,
                                            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
        Page<ResponseAdminStageDto> ongoingList = stageService.getOngoingListAdmin(pageable);
        return ResponseEntity.ok(ongoingList.getContent());
    }

    // 공연 하나 선택 했을 때 상세 페이지 조회 되게
    @GetMapping("stagedetail/{uuid}") // API 명세 => GET /shows/stagedetail/{uuid}
    public ResponseEntity<?> getStageDetail(@PathVariable @Valid String uuid) {
        return ResponseEntity.ok(stageService.stageDetail(uuid));
    }

    // StageType별로 줄세우기 -> ONGOING + 최신순 => main
    @GetMapping("stagetype/{stagetype}") // API 명세 => GET /shows/stagetype/{stagetype}
    public ResponseEntity<?> getStageTypeList(@PathVariable("stagetype")
                                              @Valid StageType stageType,
                                              @RequestParam(defaultValue = "0") int page,
                                              @RequestParam(defaultValue = "6") int size){
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
        Page<ResponseStageThumbnailDto> stageTypeList = stageService.getStageTypeList(stageType, pageable);

        return ResponseEntity.ok(stageTypeList.getContent());
    }

    // ticketOpen 시간 비교해 이전인 것만 줄 세우기 - main 커밍순
    @GetMapping("before") // API 명세 => GET /shows/before
    public ResponseEntity<?> getComingSoonList(@RequestParam(defaultValue = "0") int page,
                                               @RequestParam(defaultValue = "4") int size){
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
        Page<ResponseStageThumbnailDto> comingSoonList = stageService.getComingSoonList(pageable);

        return ResponseEntity.ok(comingSoonList.getContent());
    }

    // ticketOpen 시간 비교해 이전인 것만 줄 세우기 => admin
    @GetMapping("before/admin") // API 명세 => GET /shows/before
    public ResponseEntity<?> getComingSoonListAdmin(@RequestParam(defaultValue = "0") int page,
                                               @RequestParam(defaultValue = "10") int size){
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
        Page<ResponseAdminStageDto> comingSoonList = stageService.getComingSoonListAdmin(pageable);

        return ResponseEntity.ok(comingSoonList.getContent());
    }

    // stageClose 시간 비교해 이후인 것 줄 세우기 - 관리자 - 공연 종료
    @GetMapping("ended") // API 명세 => GET /shows/ended
    public ResponseEntity<?> getEndedList(@RequestParam(defaultValue = "0") int page,
                                          @RequestParam(defaultValue = "10") int size){
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
        Page<ResponseAdminStageDto> endedList = stageService.getEndedList(pageable);

        return ResponseEntity.ok(endedList.getContent());
    }

    // keyword로 검색
    @GetMapping("search/{keyword}") // API 명세 => GET /shows/search/{keyword}
    public ResponseEntity<?> searchStage(@PathVariable("keyword") @Valid String keyword) {
        List<ResponseStageThumbnailDto> stageThumbnailDtos = stageService.searchStage(keyword);

        if (stageThumbnailDtos.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("해당 검색어의 검색 결과가 없습니다.");
        }

        return ResponseEntity.ok(stageThumbnailDtos);
    }

    @PatchMapping("update/{uuid}") // API 명세 => PATCH /shows/update/{uuid}
    public ResponseEntity<?> updateInfo(@PathVariable String uuid,
                                        @RequestBody @Valid RequestUpdateInfoDto updateInfoDto) {
        stageService.updateInfo(uuid, updateInfoDto);

        return ResponseEntity.ok("수정이 완료되었습니다.");
    }

    @DeleteMapping("{uuid}") // API 명세 => DELETE /shows/{uuid}
    public ResponseEntity<?> deleteStage(@PathVariable @Valid String uuid) {
        stageService.deleteStage(uuid);

        return ResponseEntity.ok("삭제가 완료되었습니다.");
    }
}
