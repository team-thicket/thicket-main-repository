package com.example.thicketstage.service;

import com.example.thicketstage.domain.Stage;
import com.example.thicketstage.domain.StageStart;
import com.example.thicketstage.dto.request.RequestCreateStageStartDto;
import com.example.thicketstage.dto.response.ResponseStageStartDto;
import com.example.thicketstage.repository.StageRepository;
import com.example.thicketstage.repository.StageStartRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class StageStartServiceImpl implements StageStartService {

    private final StageStartRepository stageStartRepository;
    private final StageRepository stageRepository;

    @Override
    @Transactional
    public List<StageStart> createStageStart(RequestCreateStageStartDto dto) {

        Stage stage = stageRepository.findById(dto.getStageId())
                .orElseThrow(() -> new EntityNotFoundException("해당 공연을 찾을 수 없습니다."));
        log.info(dto.getStageStartDtos().get(0).getTime().toString());
        List<StageStart> stageStarts = dto.getStageStartDtos().stream()
                .map(ss -> StageStart.createStageStart(ss.getDate(), ss.getTime(), stage))
                .toList();

        return stageStartRepository.saveAll(stageStarts);
    }

    @Override
    public List<ResponseStageStartDto> getAllDate(){
        List<StageStart> findStageStarts = stageStartRepository.findAll();
        List<ResponseStageStartDto> listStageStart = findStageStarts.stream()
                                                    .map(ResponseStageStartDto::new).toList();
        if(listStageStart.isEmpty()){
            throw new EntityNotFoundException("회차 정보가 존재하지 않습니다.");
        }

        return listStageStart;
    }

    @Override
    public List<ResponseStageStartDto> getStageAllStageStart(UUID stageId) {
        Stage stage = stageRepository.findById(stageId)
                .orElseThrow(() -> new EntityNotFoundException("해당 공연을 찾을 수 없습니다."));

        List<StageStart> allStageStarts = stageStartRepository.findByStage(stage);

        return allStageStarts.stream().map(ResponseStageStartDto::new).toList();
    }

    // 회차 정보 수정은 추후 고도화 구현시 구현 예정
//    @Override
//    @Transactional
//    public void updateStageStart(Long id, RequestStageStartUpdateDto stageStartUpdateDto){
//
//        Optional<StageStart> optionalStageStart = stageStartRepository.findById(id);
//
//        if(optionalStageStart.isEmpty()){
//            throw new EntityNotFoundException("해당 Id값의 일정이 없습니다.");
//        }
//        StageStart stageStart = optionalStageStart.get();
//
//        stageStart.updateStageStart(stageStartUpdateDto);
//    }

    @Override
    @Transactional
    public void deleteStageStart(UUID id) {
        Optional<StageStart> optionalStageStart = stageStartRepository.findById(id);

        if(optionalStageStart.isEmpty()){
            throw new EntityNotFoundException("해당 회차 정보를 찾을 수 없습니다.");
        }

        StageStart stageStart = optionalStageStart.get();

        stageStart.deleteStageStart();
        stageStartRepository.delete(stageStart);
    }
}