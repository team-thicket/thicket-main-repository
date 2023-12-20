package com.example.thicketstage.service;

import com.example.thicketstage.domain.Stage;
import com.example.thicketstage.dto.request.RequestCreateStageDto;
import com.example.thicketstage.dto.request.RequestUpdateInfoDto;
import com.example.thicketstage.dto.response.ResponseAdminStageDto;
import com.example.thicketstage.dto.response.ResponseStageDto;
import com.example.thicketstage.dto.response.ResponseStageThumbnailDto;
import com.example.thicketstage.enumerate.StageType;
import com.example.thicketstage.repository.StageRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StageServiceImpl implements StageService{

    private final StageRepository stageRepository;

    @Override
    public RequestCreateStageDto createStage(RequestCreateStageDto stageDto) {
        Stage stage = stageDto.toEntity();

        stageRepository.save(stage);
        
        return new RequestCreateStageDto();
    }

    @Override
    public List<ResponseAdminStageDto> getAllStage(){
        List<Stage> all = stageRepository.findAll();

        ArrayList<ResponseAdminStageDto> dtos = new ArrayList<>();
        for (Stage stage : all) {
            dtos.add(new ResponseAdminStageDto(stage));
        }

        if(dtos.isEmpty()) {
            throw new EntityNotFoundException("공연이 존재하지 않습니다.");
        }

        return dtos;
    }

    // 진행 중인 공연 모두 최신 순으로 => main
    @Override
    public Page<ResponseStageThumbnailDto> getOngoingList(Pageable pageable){
        LocalDateTime now = LocalDateTime.now();

        List<Stage> allStages = stageRepository.findAll();
        List<Stage> ongoingStages = allStages.stream()
                .filter(stage -> stage.getStageOpen().isBefore(now)
                                    && stage.getStageClose().isAfter(now))
                .collect(Collectors.toList());

        if (ongoingStages.isEmpty()) {
            throw new EntityNotFoundException("현재 진행 중인 공연이 없습니다.");
        }

        return new PageImpl<>(ongoingStages, pageable, ongoingStages.size())
                                        .map(ResponseStageThumbnailDto::new);
    }

    // 진행 중인 공연 모두 최신순으로 => admin
    @Override
    public Page<ResponseAdminStageDto> getOngoingListAdmin(Pageable pageable){
        LocalDateTime now = LocalDateTime.now();

        List<Stage> allStages = stageRepository.findAll();
        List<Stage> ongoingStages = allStages.stream()
                .filter(stage -> stage.getStageOpen().isBefore(now)
                                    && stage.getStageClose().isAfter(now))
                .collect(Collectors.toList());

        if (ongoingStages.isEmpty()) {
            throw new EntityNotFoundException("현재 진행 중인 공연이 없습니다.");
        }

        return new PageImpl<>(ongoingStages, pageable, ongoingStages.size())
                                                       .map(ResponseAdminStageDto::new);
    }

    // 공연 하나 선택 했을 때 상세 페이지 조회 되게
    @Override
    public ResponseStageDto stageDetail(String uuid) {
        Optional<Stage> optionalStage = stageRepository.findByUuid(uuid);

        if(optionalStage.isEmpty()){
            throw new EntityNotFoundException("해당 공연이 존재하지 않습니다");
        }
        Stage stage = optionalStage.get();

        return new ResponseStageDto(stage);
    }

    // StageType 별로 줄 세우기 - 진행 중인 공연 최신 등록 순으로 정렬
    @Override
    public Page<ResponseStageThumbnailDto> getStageTypeList(StageType stageType,
                                                             Pageable pageable) {
        LocalDateTime now = LocalDateTime.now();

        List<Stage> stages = stageRepository.findByStageType(stageType);
        List<Stage> ongoingStages = stages.stream()
                .filter(stage -> stage.getStageOpen().isBefore(now)
                                    && stage.getStageClose().isAfter(now))
                .toList();

        if(ongoingStages.isEmpty()){
            throw new EntityNotFoundException("현재 진행 중인 공연이 없습니다.");
        }

        return new PageImpl<>(ongoingStages, pageable, ongoingStages.size())
                                                         .map(ResponseStageThumbnailDto::new);
    }

    // ticketOpen시간 비교해서 이전인것만 - 커밍순 main /shows/before
    @Override
    public Page<ResponseStageThumbnailDto> getComingSoonList(Pageable pageable){
        LocalDateTime now = LocalDateTime.now();

        List<Stage> allStages = stageRepository.findAll();
        List<Stage> comingSoonStages = allStages.stream()
                .filter(stage -> stage.getTicketOpen().isAfter(now))
                .toList();

        if (comingSoonStages.isEmpty()) {
            throw new EntityNotFoundException("개막 예정인 공연이 없습니다.");
        }

        return new PageImpl<>(comingSoonStages, pageable, comingSoonStages.size())
                                             .map(ResponseStageThumbnailDto::new);
    }

    // ticketOpen시간 비교해서 이전인것만 => 관리자
    @Override
    public Page<ResponseAdminStageDto> getComingSoonListAdmin(Pageable pageable){
        LocalDateTime now = LocalDateTime.now();

        List<Stage> allStages = stageRepository.findAll();
        List<Stage> comingSoonStages = allStages.stream()
                .filter(stage -> stage.getTicketOpen().isAfter(now))
                .toList();

        if (comingSoonStages.isEmpty()) {
            throw new EntityNotFoundException("개막 예정인 공연이 없습니다.");
        }

        return new PageImpl<>(comingSoonStages, pageable, comingSoonStages.size())
                                                            .map(ResponseAdminStageDto::new);
    }

    // stageClose보다 이후 - 관리자 page -> /shows/ended
    @Override
    public Page<ResponseAdminStageDto> getEndedList(Pageable pageable){
        LocalDateTime now = LocalDateTime.now();

        List<Stage> allStages = stageRepository.findAll();
        List<Stage> endedStages = allStages.stream()
                .filter(stage -> stage.getStageClose().isBefore(now))
                .toList();

        if (endedStages.isEmpty()) {
            throw new EntityNotFoundException("종료된 공연이 없습니다.");
        }

        return new PageImpl<>(endedStages, pageable, endedStages.size())
                                                        .map(ResponseAdminStageDto::new);
    }

    @Override
    public List<ResponseStageThumbnailDto> searchStage(String keyword) {
        List<Stage> searchResults = stageRepository.searchByNameOrPlace("%"+keyword+"%");

        ArrayList<ResponseStageThumbnailDto> stageInfoList = new ArrayList<>();

        for (Stage stage : searchResults) {
            ResponseStageThumbnailDto stageInfoDto = new ResponseStageThumbnailDto(stage);
            stageInfoList.add(stageInfoDto);
        }

        return stageInfoList;
    }

    @Override
    @Transactional
    public void updateInfo(String uuid, RequestUpdateInfoDto updateInfoDto) {

        Stage stage = stageRepository.findByUuid(uuid)
                .orElseThrow(() -> new EntityNotFoundException("공연을 찾을 수 없습니다."));
        stage.updateStageInfo(updateInfoDto);
    }

    @Override
    @Transactional
    public void deleteStage(String uuid) {
        Optional<Stage> optionalStage = stageRepository.findByUuid(uuid);

        if(optionalStage.isEmpty()){
            throw new EntityNotFoundException("공연을 찾을 수 없습니다.");
        }
        Stage stage = optionalStage.get();
        stageRepository.delete(stage);
    }
}