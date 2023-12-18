package com.example.thicketstage.service;

import com.example.thicketstage.dto.request.RequestCreateStageDto;
import com.example.thicketstage.dto.request.RequestUpdateInfoDto;
import com.example.thicketstage.dto.response.ResponseStageDto;
import com.example.thicketstage.dto.response.ResponseStageThumbnailDto;
import com.example.thicketstage.enumerate.StageType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface StageService {

    public RequestCreateStageDto createStage(RequestCreateStageDto stageDto);

    List<ResponseStageThumbnailDto> getAllStage();

    Page<ResponseStageThumbnailDto> getOngoingList(Pageable pageable);

    public ResponseStageDto stageDetail(String uuid);

    Page<ResponseStageThumbnailDto> getStageTypeList(StageType stageType, Pageable pageable);

    Page<ResponseStageThumbnailDto> getComingSoonList(Pageable pageable);

    Page<ResponseStageThumbnailDto> getEndedList(Pageable pageable);

    List<ResponseStageThumbnailDto> searchStage(String keyword);

    public void updateInfo(String uuid, RequestUpdateInfoDto updateInfoDto);

    public void deleteStage(String uuid);
}