package com.example.thicketstage.service;

import com.example.thicketstage.domain.Chair;
import com.example.thicketstage.dto.request.RequestCreateChairDto;
import com.example.thicketstage.dto.request.RequestUpdateChairDto;
import com.example.thicketstage.dto.response.ResponseChairDto;

import java.util.List;

public interface ChairService {

    public List<Chair> createChair(RequestCreateChairDto dto);

    // 단일 조회
    public ResponseChairDto findChairByUuid(String uuid);

    // 전체 조회
    public List<ResponseChairDto> getAllChair();

    public List<ResponseChairDto> getStageStartAllChair(String stageStartUuid);

    // 수정 - 추후 고도화 시 구현예정
    public void updateChair(String uuid, RequestUpdateChairDto updateChairDto);

    //삭제
    public void deleteChair(String uuid);

}
