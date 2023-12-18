package com.example.thicketstage.service;

import com.example.thicketstage.domain.Stage;
import com.example.thicketstage.dto.request.RequestCreateStageDto;
import com.example.thicketstage.dto.request.RequestUpdateInfoDto;
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
    public List<ResponseStageThumbnailDto> getAllStage(){
        List<Stage> all = stageRepository.findAll();

        ArrayList<ResponseStageThumbnailDto> dtos = new ArrayList<>();
        for (Stage stage : all) {
            dtos.add(new ResponseStageThumbnailDto(stage));
        }
        return dtos;
    }

    // 진행 중인 공연 모두 최신 순으로
    @Override
    public Page<ResponseStageThumbnailDto> getOngoingList(Pageable pageable){
        LocalDateTime now = LocalDateTime.now();

        List<Stage> allStages = stageRepository.findAll();
        List<Stage> ongoingStages = allStages.stream()
                .filter(stage -> stage.getStageOpen().isBefore(now) && stage.getStageClose().isAfter(now))
                .collect(Collectors.toList());

        if (ongoingStages.isEmpty()) {
            throw new EntityNotFoundException("현재 진행 중인 공연이 없습니다.");
        }

        return new PageImpl<>(ongoingStages, pageable, ongoingStages.size())
                .map(ResponseStageThumbnailDto::new);
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

    // StageType 별로 줄 세우기 - 진행 중인 공연 최신 등록 순으로 정렬 + 페이징처리
    @Override
    public Page<ResponseStageThumbnailDto> getStageTypeList(StageType stageType, Pageable pageable) {
        LocalDateTime now = LocalDateTime.now();

        List<Stage> stages = stageRepository.findByStageType(stageType);
        List<Stage> ongoingStages = stages.stream()
                .filter(stage -> stage.getStageOpen().isBefore(now) && stage.getStageClose().isAfter(now))
                .toList();

        if(ongoingStages.isEmpty()){
            throw new EntityNotFoundException("현재 진행 중인 공연이 없습니다.");
        }

        return new PageImpl<>(ongoingStages, pageable, ongoingStages.size())
                .map(ResponseStageThumbnailDto::new);
    }

    // -> /shows/before - ticketopen시간 비교해서 이전인것만 - 커밍순 main
    @Override
    public Page<ResponseStageThumbnailDto> getComingSoonList(Pageable pageable){
        LocalDateTime now = LocalDateTime.now();

        List<Stage> allStages = stageRepository.findAll();
        List<Stage> comingSoonStages = allStages.stream()
                .filter(stage -> stage.getTicketOpen().isAfter(now))
                .toList();

        if (comingSoonStages.isEmpty()) {
            throw new EntityNotFoundException("현재 진행 중인 공연이 없습니다.");
        }

        return new PageImpl<>(comingSoonStages, pageable, comingSoonStages.size())
                .map(ResponseStageThumbnailDto::new);
    }

    // -> /shows/ended - stageClose보다 이후 - 관리자 page -- 타입을 가져와서 나눠야하나???
    @Override
    public Page<ResponseStageThumbnailDto> getEndedList(Pageable pageable){
        LocalDateTime now = LocalDateTime.now();

        List<Stage> allStages = stageRepository.findAll();
        List<Stage> endedStages = allStages.stream()
                .filter(stage -> stage.getStageClose().isBefore(now))
                .toList();

        if (endedStages.isEmpty()) {
            throw new EntityNotFoundException("종료된 공연이 없습니다.");
        }

        return new PageImpl<>(endedStages, pageable, endedStages.size())
                .map(ResponseStageThumbnailDto::new);
    }

    @Override
    public List<ResponseStageThumbnailDto> searchStage(String keyword) {
        List<Stage> searchResults = stageRepository.searchByNameOrPlace("%"+keyword+"%");

        if(searchResults.isEmpty()){
            throw new EntityNotFoundException("해당 검색어의 검색 결과가 없습니다.");
        }

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
    public void deleteStage(String uuid) {
        Optional<Stage> optionalStage = stageRepository.findByUuid(uuid);

        if(optionalStage.isEmpty()){
            throw new EntityNotFoundException("공연을 찾을 수 없습니다.");
        }
        Stage stage = optionalStage.get();
        stageRepository.delete(stage);
    }
}