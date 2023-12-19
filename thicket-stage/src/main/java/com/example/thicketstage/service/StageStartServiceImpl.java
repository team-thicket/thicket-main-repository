package com.example.thicketstage.service;

import com.example.thicketstage.domain.Stage;
import com.example.thicketstage.domain.StageStart;
import com.example.thicketstage.dto.request.RequestCreateStageStartDto;
import com.example.thicketstage.dto.response.ResponseStageStartDto;
import com.example.thicketstage.repository.ChairRepository;
import com.example.thicketstage.repository.StageRepository;
import com.example.thicketstage.repository.StageStartRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StageStartServiceImpl implements StageStartService {

    private final StageStartRepository stageStartRepository;
    private final StageRepository stageRepository;
    private final ChairRepository chairRepository;

    @Override
    @Transactional
    public List<StageStart> createStageStart(RequestCreateStageStartDto dto) {

        Stage stage = stageRepository.findByUuid(dto.getStageUuid())
                .orElseThrow(() -> new EntityNotFoundException("해당 공연을 찾을 수 없습니다."));

        List<StageStart> stageStarts = dto.getStageStartDtos().stream()
                .map(ss -> StageStart.createStageStart(ss.getDate(), ss.getTime(), stage))
                .toList();

        return stageStartRepository.saveAll(stageStarts);
    }

    @Override
    public List<ResponseStageStartDto> getAllDate(){
        List<StageStart> findStageStarts = stageStartRepository.findAll();
        return findStageStarts.stream().map(ResponseStageStartDto::new).toList();
    }

    @Override
    public List<ResponseStageStartDto> getStageAllStageStart(String stageUuid) {
        Stage findStage = stageRepository.findByUuid(stageUuid)
                .orElseThrow(() -> new EntityNotFoundException("해당 UUID의 Stage가 존재하지 않습니다."));
        List<StageStart> allStageStarts = stageStartRepository.findByStage(findStage);

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
    public void deleteStageStart(String uuid) {
        Optional<StageStart> optionalStageStart = stageStartRepository.findByUuid(uuid);

        if(optionalStageStart.isEmpty()){
            throw new EntityNotFoundException("해당 공연을 찾을 수 없습니다.");
        }

        StageStart stageStart = optionalStageStart.get();

        stageStart.deleteStageStart();
        stageStartRepository.delete(stageStart);
    }
}