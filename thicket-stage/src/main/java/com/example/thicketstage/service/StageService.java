package com.example.thicketstage.service;

import com.example.thicketstage.dto.request.RequestCreateStageDto;
import com.example.thicketstage.dto.request.RequestSetNewStatusDto;
import com.example.thicketstage.dto.request.RequestUpdateInfoDto;
import com.example.thicketstage.dto.response.ResponseStageDto;
import com.example.thicketstage.dto.response.ResponseStageThumbnailDto;
import com.example.thicketstage.enumerate.StageType;

import java.util.List;

public interface StageService {

    public RequestCreateStageDto createStage(RequestCreateStageDto stageDto);

    List<ResponseStageThumbnailDto> getAllStage();

    public ResponseStageDto stageDetail(Long id);

    public List<ResponseStageThumbnailDto> getStageTypeList(StageType stageType);

    List<ResponseStageThumbnailDto> searchStage(String keyword);

    public void updateInfo(Long id, RequestUpdateInfoDto updateInfoDto);

    public void changeStatus(Long id, RequestSetNewStatusDto setNewStatusDto);

    public void deleteStage(Long id);
    // 추후 삭제할 때 사용자 확인 로직 추가하면 Long id와 RequestDeleteStageDto 함께 사용
}