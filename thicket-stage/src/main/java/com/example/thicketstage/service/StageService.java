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

    public ResponseStageDto stageDetail(String uuid);

    public List<ResponseStageThumbnailDto> getStageTypeList(StageType stageType);

    List<ResponseStageThumbnailDto> searchStage(String keyword);

    public void updateInfo(String uuid, RequestUpdateInfoDto updateInfoDto);

    public void changeStatus(String uuid, RequestSetNewStatusDto setNewStatusDto);

    public void deleteStage(String uuid);
}