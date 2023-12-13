package com.example.thicketstage.service;

import com.example.thicketstage.dto.request.RequestCreateChairDto;
import com.example.thicketstage.dto.request.RequestUpdateChairDto;
import com.example.thicketstage.dto.response.ResponseChairDto;

import java.util.List;

public interface ChairService {

    // 생성
    void createChair(RequestCreateChairDto dto);

    // 단일 조회
    ResponseChairDto findChairByByUuid(String uuid);

    // 전체 조회
    List<ResponseChairDto> getAllChairs();

    // 수정
    void updateChair(String uuid, RequestUpdateChairDto dto);

    // 삭제
    void deleteChair(String uuid);
}
