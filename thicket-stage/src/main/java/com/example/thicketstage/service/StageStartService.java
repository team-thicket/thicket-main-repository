package com.example.thicketstage.service;

import com.example.thicketstage.domain.StageStart;
import com.example.thicketstage.dto.request.RequestCreateStageStartDto;
import com.example.thicketstage.dto.response.ResponseStageStartDto;

import java.util.List;

public interface StageStartService {

    List<StageStart> createStageStart(RequestCreateStageStartDto createStageStartDto);

    List<ResponseStageStartDto> getAllDate();
    // 회차 정보 수정은 추후 고도화 구현시 구현 예정
//    void updateStageStart(Long id, RequestStageStartUpdateDto stageStartUpdateDto);

    void deleteStageStart(String uuid);

}