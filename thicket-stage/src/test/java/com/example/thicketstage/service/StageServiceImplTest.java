package com.example.thicketstage.service;

import com.example.thicketstage.domain.Stage;
import com.example.thicketstage.domain.StageStart;
import com.example.thicketstage.dto.request.*;
import com.example.thicketstage.dto.response.ResponseStageDto;
import com.example.thicketstage.dto.response.ResponseStageThumbnailDto;
import com.example.thicketstage.enumerate.StageStatus;
import com.example.thicketstage.enumerate.StageType;
import com.example.thicketstage.repository.StageRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StageServiceImplTest {

    @Autowired
    StageRepository stageRepository;
    @Autowired
    StageService stageService;

    @Test
    @Transactional
    void createStage() {
        //given
        RequestCreateStageDto createDto = new RequestCreateStageDto();
        List<RequestCreateDateDto> createDateDtos = new ArrayList<>();
        RequestCreateDateDto createDateDto = new RequestCreateDateDto();

        createDto.setName("뮤지컬<마리퀴리>");
        createDto.setPlace("홍익대 아트센터 대극장");
        createDto.setStageOpen(LocalDateTime.of(2023, 11, 25, 19, 30));
        createDto.setStageClose(LocalDateTime.of(2024, 2, 7, 19, 30));
        createDto.setRunningTime("180분");
        createDto.setAgeLimit("8세이상 관람가");
        createDto.setStageType(StageType.MUSICAL);
        createDto.setStageStatus(StageStatus.BEFORE);
        createDto.setPosterImg("포스터 링크");
        createDto.setDetailPosterImg("상세 포스터 링크");
        createDto.setStageInfo("공연 상세 설명");

        createDateDto.setDate(LocalDate.of(2023, 11, 30));

        List<LocalTime> times = List.of(LocalTime.of(14, 30), LocalTime.of(19, 30));
        createDateDto.setTimes(times);
        createDateDtos.add(createDateDto);

        //when
        RequestCreateStageDto createStageDto = stageService.createStage(createDto, createDateDtos);

        //then
        Stage savedStage = stageRepository.findById(createStageDto.getId())
                .orElseThrow(() -> new AssertionError("저장된 공연이 없습니다."));

        assertEquals(createDto.getName(), savedStage.getName());
        assertEquals(createDto.getPlace(), savedStage.getPlace());
        assertEquals(createDto.getStageOpen(), savedStage.getStageOpen());
        assertEquals(createDto.getStageClose(), savedStage.getStageClose());
        assertEquals(createDto.getRunningTime(), savedStage.getRunningTime());
        assertEquals(createDto.getAgeLimit(), savedStage.getAgeLimit());
        assertEquals(createDto.getStageType(), savedStage.getStageType());
        assertEquals(createDto.getStageStatus(), savedStage.getStageStatus());
        assertEquals(createDateDto.getDate(), savedStage.getStageStart().get(0).getDate());
        assertEquals(createDateDto.getTimes().get(0), savedStage.getStageStart().get(0).getTimes().get(0));
        assertEquals(createDateDto.getTimes().get(1), savedStage.getStageStart().get(1).getTimes().get(1));
        assertEquals(createDto.getPosterImg(), savedStage.getPosterImg());
        assertEquals(createDto.getDetailPosterImg(), savedStage.getDetailPosterImg());
        assertEquals(createDto.getStageInfo(), savedStage.getStageInfo());
    }

    @Test
    @Transactional
    void searchStage() {
        //given
        Stage stage1 = Stage.createStage(
                "뮤지컬<마리퀴리>",
                "홍익대 아트센터 대극장",
                LocalDateTime.of(2023,11,25,19,30),
                LocalDateTime.of(2024,2,7,19,30),
                "180분",
                "8세이상 관람가",
                StageType.MUSICAL,
                StageStatus.BEFORE,
                "포스터 링크",
                "상세 포스터 링크",
                "공연 상세 설명"
        );
        List<LocalTime> times = new ArrayList<>(List.of(LocalTime.of(14, 30), LocalTime.of(19, 30)));
        StageStart.createStageStart(stage1,
                LocalDate.of(2023, 11, 30), times);
        StageStart.createStageStart(stage1,
                LocalDate.of(2023, 12, 1), times);
        stageRepository.save(stage1);

        Stage stage2 = Stage.createStage(
                "뮤지컬<#버킷리스트>",
                "국립극단 소극장 판",
                LocalDateTime.of(2023,11,25,19,30),
                LocalDateTime.of(2024,2,7,19,30),
                "100분",
                "전체 관람가",
                StageType.PLAY,
                StageStatus.ENDED,
                "포스터 링크",
                "상세 포스터 링크",
                "공연 상세 설명"
        );
        List<LocalTime> times1 = new ArrayList<>(List.of(LocalTime.of(19, 30)));
        StageStart.createStageStart(stage2,
                LocalDate.of(2023, 12, 5), times1);
        StageStart.createStageStart(stage2,
                LocalDate.of(2023, 12, 30), times);
        stageRepository.save(stage2);

        String search = "뮤지컬";

        //when
        List<ResponseStageThumbnailDto> stageThumbnailList = stageService.searchStage(search);

        //then

        assertEquals(stageThumbnailList.size(),3);
        List<String> nameList = stageThumbnailList.stream()
                                .map(ResponseStageThumbnailDto::getName).toList();
        List<String> placeList = stageThumbnailList.stream()
                                .map(ResponseStageThumbnailDto::getPlace).toList();
        assertTrue(nameList.get(0).contains(search) && nameList.get(1).contains(search));
        assertFalse(placeList.get(0).contains(search) && placeList.get(1).contains(search));
    }

    @Test
    @Transactional
    void getAllStage() {
    //given
        Stage stage1 = Stage.createStage(
                "뮤지컬<마리퀴리>",
                "홍익대 아트센터 대극장",
                LocalDateTime.of(2023,11,25,19,30),
                LocalDateTime.of(2024,2,7,19,30),
                "180분",
                "8세이상 관람가",
                StageType.MUSICAL,
                StageStatus.BEFORE,
                "포스터 링크",
                "상세 포스터 링크",
                "공연 상세 설명"
        );
        List<LocalTime> times = new ArrayList<>(List.of(LocalTime.of(14, 30), LocalTime.of(19, 30)));
        StageStart.createStageStart(stage1,
                LocalDate.of(2023, 11, 30), times);
        StageStart.createStageStart(stage1,
                LocalDate.of(2023, 12, 1), times);
        stageRepository.save(stage1);

        Stage stage2 = Stage.createStage(
                "청소년극<#버킷리스트>",
                "국립극단 소극장 판",
                LocalDateTime.of(2023,11,25,19,30),
                LocalDateTime.of(2024,2,7,19,30),
                "100분",
                "전체 관람가",
                StageType.PLAY,
                StageStatus.ENDED,
                "포스터 링크",
                "상세 포스터 링크",
                "공연 상세 설명"
        );
        List<LocalTime> times1 = new ArrayList<>(List.of(LocalTime.of(19, 30)));
        StageStart.createStageStart(stage2,
                LocalDate.of(2023, 12, 5), times1);
        StageStart.createStageStart(stage2,
                LocalDate.of(2023, 12, 30), times);
        stageRepository.save(stage2);

    //when
        List<ResponseStageThumbnailDto> allStage = stageService.getAllStage();
    //then
        assertEquals(4, allStage.size());
        assertTrue(allStage.stream().anyMatch(dto -> dto.getName().equals("뮤지컬<마리퀴리>")));
        assertTrue(allStage.stream().anyMatch(dto -> dto.getName().equals("청소년극<#버킷리스트>")));
    }

    @Test
    @Transactional
    void StageDetail() {
        // given
        Stage savedStage =Stage.createStage(
                "뮤지컬<마리퀴리>",
                "홍익대 아트센터 대극장",
                LocalDateTime.of(2023,11,25,19,30),
                LocalDateTime.of(2024,2,7,19,30),
                "180분",
                "8세이상 관람가",
                StageType.MUSICAL,
                StageStatus.BEFORE,
                "포스터 링크",
                "상세 포스터 링크",
                "공연 상세 설명"
        );
        List<LocalTime> times = new ArrayList<>(List.of(LocalTime.of(14, 30), LocalTime.of(19, 30)));
        StageStart savedStageStart1 = StageStart.createStageStart(savedStage,
                LocalDate.of(2023, 11, 30), times);
        StageStart savedStageStart2 = StageStart.createStageStart(savedStage,
                LocalDate.of(2023, 12, 1), times);
        stageRepository.save(savedStage);
        Long id = savedStage.getId();

        // when
        ResponseStageDto findResponseStageDto = stageService.stageDetail(id);

        //then
        assertNotNull(findResponseStageDto);
        assertEquals("뮤지컬<마리퀴리>", findResponseStageDto.getName());
        assertEquals("홍익대 아트센터 대극장", findResponseStageDto.getPlace());
        assertEquals(LocalDateTime.of(2023, 11, 25, 19, 30),
                                        findResponseStageDto.getStageOpen());
        assertEquals(LocalDateTime.of(2024,2,7,19,30),
                                        findResponseStageDto.getStageClose());
        assertEquals("180분", findResponseStageDto.getRunningTime());
        assertEquals("8세이상 관람가", findResponseStageDto.getAgeLimit());
        assertEquals(StageType.MUSICAL, findResponseStageDto.getStageType());
        assertEquals(StageStatus.BEFORE, findResponseStageDto.getStageStatus());
        assertEquals(List.of(savedStageStart1, savedStageStart2).toString(), findResponseStageDto.getStageStart().toString());
        assertEquals("포스터 링크", findResponseStageDto.getPosterImg());
        assertEquals("상세 포스터 링크", findResponseStageDto.getDetailPosterImg());
        assertEquals("공연 상세 설명", findResponseStageDto.getStageInfo());
    }

    @Test
    @Transactional
    void getStageTypeList() {
        //given
        Stage stage1 = Stage.createStage(
                "뮤지컬<마리퀴리>",
                "홍익대 아트센터 대극장",
                LocalDateTime.of(2023,11,25,19,30),
                LocalDateTime.of(2024,2,7,19,30),
                "180분",
                "8세이상 관람가",
                StageType.MUSICAL,
                StageStatus.BEFORE,
                "포스터 링크",
                "상세 포스터 링크",
                "공연 상세 설명"
        );
        List<LocalTime> times = new ArrayList<>(List.of(LocalTime.of(14, 30), LocalTime.of(19, 30)));
        StageStart.createStageStart(stage1,
                LocalDate.of(2023, 11, 30), times);
        StageStart.createStageStart(stage1,
                LocalDate.of(2023, 12, 1), times);
        stageRepository.save(stage1);

        Stage stage2 = Stage.createStage(
                "청소년극<#버킷리스트>",
                "국립극단 소극장 판",
                LocalDateTime.of(2023,11,25,19,30),
                LocalDateTime.of(2024,2,7,19,30),
                "100분",
                "전체 관람가",
                StageType.PLAY,
                StageStatus.ENDED,
                "포스터 링크",
                "상세 포스터 링크",
                "공연 상세 설명"
        );
        List<LocalTime> times1 = new ArrayList<>(List.of(LocalTime.of(19, 30)));
        StageStart.createStageStart(stage2,
                LocalDate.of(2023, 12, 5), times1);
        StageStart.createStageStart(stage2,
                LocalDate.of(2023, 12, 30), times);
        stageRepository.save(stage2);

        //when
        List<ResponseStageThumbnailDto> stageTypeList = stageService.getStageTypeList(StageType.PLAY);
        //then
        assertEquals(2, stageTypeList.size());
        assertTrue(stageTypeList.stream().allMatch(dto -> dto.getName().equals("청소년극<#버킷리스트>")));
    }

    @Test
    @Transactional
    void updateInfo() {
        //given
        Stage savedStage =Stage.createStage(
                "뮤지컬<마리퀴리>",
                "홍익대 아트센터 대극장",
                LocalDateTime.of(2023,11,25,19,30),
                LocalDateTime.of(2024,2,7,19,30),
                "180분",
                "8세이상 관람가",
                StageType.MUSICAL,
                StageStatus.BEFORE,
                "포스터 링크",
                "상세 포스터 링크",
                "공연 상세 설명"
        );
        List<LocalTime> times = new ArrayList<>(List.of(LocalTime.of(14, 30), LocalTime.of(19, 30)));
        StageStart.createStageStart(savedStage,
                LocalDate.of(2023, 11, 30), times);
        StageStart.createStageStart(savedStage,
                LocalDate.of(2023, 12, 1), times);
        stageRepository.save(savedStage);
        Long id = savedStage.getId();

        String newStageName = "뮤지컬<렌트>";
        RequestUpdateInfoDto updateInfoDto = new RequestUpdateInfoDto();
        updateInfoDto.setName(newStageName);

        //when
        stageService.updateInfo(id, updateInfoDto);

        //then
        Stage updatedStage = stageRepository.findById(id)
                .orElseThrow(() -> new AssertionError("업데이트된 정보가 없습니다."));

        assertEquals(newStageName, updatedStage.getName());
    }

    @Test
    @Transactional
    void changeStatus() {
        //given
        Stage savedStage =Stage.createStage(
                "뮤지컬<마리퀴리>",
                "홍익대 아트센터 대극장",
                LocalDateTime.of(2023,11,25,19,30),
                LocalDateTime.of(2024,2,7,19,30),
                "180분",
                "8세이상 관람가",
                StageType.MUSICAL,
                StageStatus.BEFORE,
                "포스터 링크",
                "상세 포스터 링크",
                "공연 상세 설명"
        );
        List<LocalTime> times = new ArrayList<>(List.of(LocalTime.of(14, 30), LocalTime.of(19, 30)));
        StageStart.createStageStart(savedStage,
                LocalDate.of(2023, 11, 30), times);
        StageStart.createStageStart(savedStage,
                LocalDate.of(2023, 12, 1), times);
        stageRepository.save(savedStage);
        Long id = savedStage.getId();

        RequestSetNewStatusDto setNewStatusDto = new RequestSetNewStatusDto();
        setNewStatusDto.setNewStatus(StageStatus.ONGOING);

        //when
        stageService.changeStatus(id, setNewStatusDto);

        //then
        Stage updatedStage = stageRepository.findById(id)
                .orElseThrow(() -> new AssertionError("상태 변경된 공연이 없습니다."));

        assertEquals(StageStatus.ONGOING, updatedStage.getStageStatus());
    }

    @Test
    @Transactional
    void deleteStage() {
        //given
        Stage savedStage =Stage.createStage(
                "뮤지컬<마리퀴리>",
                "홍익대 아트센터 대극장",
                LocalDateTime.of(2023,11,25,19,30),
                LocalDateTime.of(2024,2,7,19,30),
                "180분",
                "8세이상 관람가",
                StageType.MUSICAL,
                StageStatus.BEFORE,
                "포스터 링크",
                "상세 포스터 링크",
                "공연 상세 설명"
        );
        List<LocalTime> times = new ArrayList<>(List.of(LocalTime.of(14, 30), LocalTime.of(19, 30)));
        StageStart.createStageStart(savedStage,
                LocalDate.of(2023, 11, 30), times);
        StageStart.createStageStart(savedStage,
                LocalDate.of(2023, 12, 1), times);
        stageRepository.save(savedStage);
        Long id = savedStage.getId();

        RequestDeleteStageDto deleteStageDto = new RequestDeleteStageDto();

        //when
        stageService.deleteStage(id);

        //then
        assertThrows(EntityNotFoundException.class,
                () -> stageRepository.findById(id).orElseThrow(EntityNotFoundException::new));
    }
}
