package com.example.thicketstage.service;

import com.example.thicketstage.domain.StageStart;
import com.example.thicketstage.dto.request.RequestCreateStageStartDto;
import com.example.thicketstage.dto.response.ResponseStageStartDto;

import java.util.List;

public interface StageStartService {

    List<StageStart> createStageStart(RequestCreateStageStartDto createStageStartDto);

    List<ResponseStageStartDto> getAllDate();

//    void updateStageStart(Long id, RequestStageStartUpdateDto stageStartUpdateDto);

    void deleteStageStart(Long id);

}