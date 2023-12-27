package com.example.thicketstage.service;

import com.example.thicketstage.dto.request.RequestCreateStageDto;
import com.example.thicketstage.dto.request.RequestUpdateInfoDto;
import com.example.thicketstage.dto.response.ResponseAdminStageDto;
import com.example.thicketstage.dto.response.ResponseStageDto;
import com.example.thicketstage.dto.response.ResponseStageThumbnailDto;
import com.example.thicketstage.enumerate.StageType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

public interface StageService {

    String createStage(RequestCreateStageDto stageDto);

    List<String> uploadImage(List<MultipartFile> images);

    List<ResponseAdminStageDto> getAllStage();

    Page<ResponseStageThumbnailDto> getOngoingList(Pageable pageable);

    Page<ResponseAdminStageDto> getOngoingListAdmin(Pageable pageable);

    public ResponseStageDto stageDetail(UUID id);

    Page<ResponseStageThumbnailDto> getStageTypeList(StageType stageType, Pageable pageable);

    Page<ResponseStageThumbnailDto> getComingSoonList(Pageable pageable);

    Page<ResponseAdminStageDto> getComingSoonListAdmin(Pageable pageable);

    Page<ResponseAdminStageDto> getEndedList(Pageable pageable);

    List<ResponseStageThumbnailDto> searchStage(String keyword);

    void updateInfo(UUID id, RequestUpdateInfoDto updateInfoDto);

    void deleteStage(UUID id);
}