package com.example.thicketstage.service;

import com.example.thicketstage.domain.Stage;
import com.example.thicketstage.dto.request.RequestCreateStageDto;
import com.example.thicketstage.dto.request.RequestSetNewStatusDto;
import com.example.thicketstage.dto.request.RequestUpdateInfoDto;
import com.example.thicketstage.dto.response.ResponseStageDto;
import com.example.thicketstage.dto.response.ResponseStageThumbnailDto;
import com.example.thicketstage.enumerate.StageStatus;
import com.example.thicketstage.enumerate.StageType;
import com.example.thicketstage.repository.StageRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    // STATUS.ONGOING 인 공연 모두 최신 순으로 - 작성중
//    @Override
//    public Page<ResponseStageThumbnailDto> getOngoingList(StageStatus stageStatus,
//                                                          Pageable pageable) {
//        Page<Stage> stages
//                = stageRepository.findByStageStatusOrderByCreateAtDesc(StageStatus.ONGOING,
//                                                                        pageable);
//        if(stages.isEmpty()){
//            throw new EntityNotFoundException("해당 공연이 존재하지 않습니다");
//        }
//        return stages.map(ResponseStageThumbnailDto::new);
//    }

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

    // StageType 별로 줄 세우기
    @Override
    public List<ResponseStageThumbnailDto> getStageTypeList(StageType stageType) {
        List<Stage> stages = stageRepository.findByStageType(stageType);

        if(stages.isEmpty()){
            throw new EntityNotFoundException("해당 공연이 존재하지 않습니다");
        }

        return stages.stream().map(ResponseStageThumbnailDto::new)
                                .collect(Collectors.toList());
    }

    // stageStatus 별로 줄 세우기
    @Override
    public List<ResponseStageThumbnailDto> getStageStatusList(StageStatus stageStatus){
        List<Stage> stages = stageRepository.findByStageStatus(stageStatus);

        if(stages.isEmpty()){
            throw new EntityNotFoundException("해당 공연이 존재하지 않습니다");
        }

        return stages.stream().map(ResponseStageThumbnailDto::new)
                                .collect(Collectors.toList());
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
    public void changeStatus(String uuid, RequestSetNewStatusDto setNewStatusDto) {
        Optional<Stage> optionalStage = stageRepository.findByUuid(uuid);

        if(optionalStage.isEmpty()){
            throw new EntityNotFoundException("공연을 찾을 수 없습니다.");
        }
        Stage stage = optionalStage.get();
        StageStatus newStatus = setNewStatusDto.getNewStatus();

        stage.setStageStatus(newStatus);
        stageRepository.save(stage);
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