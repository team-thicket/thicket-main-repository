package com.example.thicketstage.service;

import com.example.thicketstage.domain.StageStart;
import com.example.thicketstage.dto.request.RequestCreateStageStartDto;
import com.example.thicketstage.dto.response.ResponseStageStartDto;

import java.util.List;
import java.util.UUID;

public interface StageStartService {

    List<StageStart> createStageStart(RequestCreateStageStartDto createStageStartDto);

    List<ResponseStageStartDto> getAllDate();

    public List<ResponseStageStartDto> getStageAllStageStart(UUID stageId);

    // 회차 정보 수정은 추후 고도화 구현시 구현 예정
//    void updateStageStart(Long id, RequestStageStartUpdateDto stageStartUpdateDto);

    void deleteStageStart(UUID id);

}