package com.example.thicketstage.service;

import com.example.thicketstage.domain.Chair;
import com.example.thicketstage.dto.request.RequestCreateChairDto;
import com.example.thicketstage.dto.request.RequestUpdateChairDto;
import com.example.thicketstage.dto.response.ResponseChairDto;
import com.example.thicketstage.repository.ChairRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ChairServiceImpl implements ChairService {

    private final ChairRepository chairRepository;

    @Override
    @Transactional // 등록
    public void createChair(RequestCreateChairDto dto) {
        // Check if chairType already exists for the given stageUuid
        if (chairRepository.existsByStageUuidAndChairType(dto.getStageUuid(), dto.getChairType())) {
            throw new IllegalArgumentException("이미 존재하는 좌석 타입입니다.");
        }

        Chair chair = dto.toEntity();
        chairRepository.save(chair);
    }

    @Override // 단일 조회
    public ResponseChairDto findChairByByUuid(String uuid) {
        Chair findChair = chairRepository.findChairByUuid(uuid);

        if (findChair == null) {
            throw new IllegalArgumentException("해당 좌석이 없습니다.");
        }

        return ResponseChairDto.toDto(findChair);
    }

    @Override // 전체 조회
    public List<ResponseChairDto> getAllChairs() {
        List<Chair> allChairs = chairRepository.findAll();
        return allChairs.stream()
                .map(ResponseChairDto::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional // 수정
    public void updateChair(String uuid, RequestUpdateChairDto dto) {
        Chair findChair = chairRepository.findChairByUuid(uuid);

        if (findChair == null) {
            throw new IllegalArgumentException("해당 좌석이 없습니다.");
        }

        findChair.changeChair(dto.getNewChairType(), dto.getNewCount(), dto.getNewPrice(), dto.getNewStageUuid());
    }

    @Override
    @Transactional // 삭제
    public void deleteChair(String uuid) {
        Chair findChair = chairRepository.findChairByUuid(uuid);

        if (findChair == null) {
            throw new IllegalArgumentException("해당 좌석이 없습니다.");
        }

        chairRepository.deleteChairByUuid(uuid);
    }
}
