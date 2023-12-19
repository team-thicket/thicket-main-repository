package com.example.thicketstage.service;

import com.example.thicketstage.domain.Chair;
import com.example.thicketstage.domain.StageStart;
import com.example.thicketstage.dto.request.RequestCreateChairDto;
import com.example.thicketstage.dto.request.RequestUpdateChairDto;
import com.example.thicketstage.dto.response.ResponseChairDto;
import com.example.thicketstage.repository.ChairRepository;
import com.example.thicketstage.repository.StageStartRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChairServiceImpl implements ChairService{

    private final ChairRepository chairRepository;
    private final StageStartRepository stageStartRepository;

    @Override
    @Transactional
    public List<Chair> createChair(RequestCreateChairDto dto) {
        StageStart stageStart = stageStartRepository.findByUuid(dto.getStageStartUuid())
                .orElseThrow(() -> new EntityNotFoundException("해당 회차정보를 찾을 수 없습니다."));

        List<Chair> chairs = dto.getChairDtos().stream()
                .map(c -> Chair.createChair(c.getChairType(), c.getCount(), c.getPrice(), stageStart))
                .toList();

        return chairRepository.saveAll(chairs);
    }

    @Override // 단일조회 - 불필요시 삭제 예정
    public ResponseChairDto findChairByUuid(String uuid) {
        Optional<Chair> optionalChair = chairRepository.findByUuid(uuid);

        if(optionalChair.isEmpty()){
            throw new EntityNotFoundException("해당 좌석이 없습니다.");
        }
        Chair chair = optionalChair.get();

        return new ResponseChairDto(chair);

    }

    @Override
    public List<ResponseChairDto> getAllChair() {
        List<Chair> all = chairRepository.findAll();
        return all.stream().map(ResponseChairDto::new).toList();
    }

    @Override
    public List<ResponseChairDto> getStageStartAllChair(String stageStartUuid) {
        StageStart findStageStart = stageStartRepository.findByUuid(stageStartUuid)
                .orElseThrow(() -> new EntityNotFoundException("해당 회차 정보가 존재하지 않습니다."));
        List<Chair> allChairs = chairRepository.findByStageStart(findStageStart);

        return allChairs.stream().map(ResponseChairDto::new).toList();
    }

    // 수정 - 추후 고도화 구현시 구현?
    @Override
    @Transactional
    public void updateChair(String uuid, RequestUpdateChairDto updateChairDto){
        Optional<Chair> optionalChair = chairRepository.findByUuid(uuid);

        if(optionalChair.isEmpty()){
            throw new EntityNotFoundException("해당 좌석이 존재하지 않습니다.");
        }
        Chair chair = optionalChair.get();
        chair.updateChair(updateChairDto);
    }

    @Override
    @Transactional
    public void deleteChair(String uuid){
        Optional<Chair> optionalChair = chairRepository.findByUuid(uuid);

        if(optionalChair.isEmpty()){
            throw new EntityNotFoundException("해당 좌석을 찾을 수 없습니다.");
        }

        Chair chair = optionalChair.get();

        chair.deleteChair();
        chairRepository.delete(chair);
    }
}
