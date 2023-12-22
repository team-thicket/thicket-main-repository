package com.example.thicketstage.service;

import com.example.thicketstage.domain.Chair;
import com.example.thicketstage.dto.request.RequestCreateChairDto;
import com.example.thicketstage.dto.response.ResponseChairDto;

import java.util.List;
import java.util.UUID;

public interface ChairService {

    public List<Chair> createChair(RequestCreateChairDto dto);

    // 단일 조회
    public ResponseChairDto findChairById(UUID id);

    public List<ResponseChairDto> getStageStartAllChair(UUID stageStartId);

    // 수정 - 추후 고도화 시 구현예정
//    public void updateChair(UUID uuid, RequestUpdateChairDto updateChairDto);

    //삭제
    public void deleteChair(UUID id);

}
