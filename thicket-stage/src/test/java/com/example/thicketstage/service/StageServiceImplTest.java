package com.example.thicketstage.service;

import com.example.thicketstage.domain.Stage;
import com.example.thicketstage.dto.request.RequestCreateStageDto;
import com.example.thicketstage.dto.request.RequestDeleteStageDto;
import com.example.thicketstage.dto.request.RequestUpdateInfoDto;
import com.example.thicketstage.dto.response.ResponseAdminStageDto;
import com.example.thicketstage.dto.response.ResponseStageDto;
import com.example.thicketstage.dto.response.ResponseStageThumbnailDto;
import com.example.thicketstage.enumerate.StageType;
import com.example.thicketstage.repository.StageRepository;
import jakarta.persistence.EntityNotFoundException;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasProperty;
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

        createDto.setName("뮤지컬<펀홈>");
        createDto.setPlace("홍익대 아트센터 대극장");
        createDto.setTicketOpen(LocalDateTime.of(2023, 10, 25, 14, 0));
        createDto.setStageOpen(LocalDateTime.of(2023, 11, 25, 19, 30));
        createDto.setStageClose(LocalDateTime.of(2024, 2, 7, 19, 30));
        createDto.setLastTicket(LocalDateTime.of(2024, 2, 6, 0,0 ));
        createDto.setRunningTime("180분");
        createDto.setAgeLimit("8세이상 관람가");
        createDto.setStageType(StageType.MUSICAL);
        createDto.setImgLink("포스터 링크");
        createDto.setDetailImgLink("상세 포스터 링크");
        createDto.setStageInfo("공연 상세 설명");

        //when
        stageService.createStage(createDto);

        //then
        List<Stage> stages = stageRepository.findAll();

        Stage savedStage = stages.stream()
                .filter(stage -> createDto.getName().equals(stage.getName()))
                .findFirst().orElseThrow(() -> new AssertionError("저장된 공연이 없습니다."));

        assertEquals(createDto.getName(), savedStage.getName());
        assertEquals(createDto.getPlace(), savedStage.getPlace());
        assertEquals(createDto.getTicketOpen(), savedStage.getTicketOpen());
        assertEquals(createDto.getStageOpen(), savedStage.getStageOpen());
        assertEquals(createDto.getStageClose(), savedStage.getStageClose());
        assertEquals(createDto.getLastTicket(), savedStage.getLastTicket());
        assertEquals(createDto.getRunningTime(), savedStage.getRunningTime());
        assertEquals(createDto.getAgeLimit(), savedStage.getAgeLimit());
        assertEquals(createDto.getStageType(), savedStage.getStageType());
        assertEquals(createDto.getImgLink(), savedStage.getPosterImg());
        assertEquals(createDto.getDetailImgLink(), savedStage.getDetailPosterImg());
        assertEquals(createDto.getStageInfo(), savedStage.getStageInfo());
    }

    @Test
    @Transactional
    void searchStage() {
        //given
        Stage stage1 = Stage.createStage(
                "뮤지컬<마리퀴리>",
                "홍익대 아트센터 대극장",
                LocalDateTime.of(2023, 10, 25, 14, 0),
                LocalDateTime.of(2023,11,25,19,30),
                LocalDateTime.of(2024,2,7,19,30),
                LocalDateTime.of(2024, 2, 6, 0,0 ),
                "180분",
                "8세이상 관람가",
                StageType.MUSICAL,
                "포스터 링크",
                "상세 포스터 링크",
                "공연 상세 설명"
        );
        stageRepository.save(stage1);

        Stage stage2 = Stage.createStage(
                "뮤지컬<#버킷리스트>",
                "국립극단 소극장 판",
                LocalDateTime.of(2023, 10, 25, 14, 0),
                LocalDateTime.of(2023,11,25,19,30),
                LocalDateTime.of(2024,2,7,19,30),
                LocalDateTime.of(2024, 2, 6, 0,0 ),
                "100분",
                "전체 관람가",
                StageType.PLAY,
                "포스터 링크",
                "상세 포스터 링크",
                "공연 상세 설명"
        );
        stageRepository.save(stage2);

        String search = "뮤지컬";

        //when
        List<ResponseStageThumbnailDto> stageThumbnailList = stageService.searchStage(search);

        //then
        assertEquals(3, stageThumbnailList.size());
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
                "뮤지컬<펀홈>",
                "홍익대 아트센터 대극장",
                LocalDateTime.of(2023, 10, 25, 14, 0),
                LocalDateTime.of(2023,11,25,19,30),
                LocalDateTime.of(2024,2,7,19,30),
                LocalDateTime.of(2024, 2, 6, 0,0 ),
                "180분",
                "8세이상 관람가",
                StageType.MUSICAL,
                "포스터 링크",
                "상세 포스터 링크",
                "공연 상세 설명"
        );
        stageRepository.save(stage1);

        Stage stage2 = Stage.createStage(
                "청소년극<발가락육상천재>",
                "국립극단 소극장 판",
                LocalDateTime.of(2023, 10, 25, 14, 0),
                LocalDateTime.of(2023,11,25,19,30),
                LocalDateTime.of(2024,2,7,19,30),
                LocalDateTime.of(2024, 2, 6, 0,0 ),
                "100분",
                "전체 관람가",
                StageType.PLAY,
                "포스터 링크",
                "상세 포스터 링크",
                "공연 상세 설명"
        );
        stageRepository.save(stage2);

    //when
        List<ResponseAdminStageDto> allStage = stageService.getAllStage();
    //then
        assertEquals(6, allStage.size());
        assertTrue(allStage.stream().anyMatch(dto -> dto.getName().equals("뮤지컬<펀홈>")));
        assertTrue(allStage.stream().anyMatch(dto -> dto.getName().equals("청소년극<발가락육상천재>")));
    }

    @Test
    @Transactional
    void getOngoingList() {
        // given
        Stage stage1 = Stage.createStage(
                "뮤지컬<펀홈>",
                "홍익대 아트센터 대극장",
                LocalDateTime.of(2023, 10, 25, 14, 0),
                LocalDateTime.of(2023,11,25,19,30),
                LocalDateTime.of(2024,2,7,19,30),
                LocalDateTime.of(2024, 2, 6, 0,0 ),
                "180분",
                "8세이상 관람가",
                StageType.MUSICAL,
                "포스터 링크",
                "상세 포스터 링크",
                "공연 상세 설명"
        );

        Stage stage2 = Stage.createStage(
                "청소년극<발가락육상천재>",
                "국립극단 소극장 판",
                LocalDateTime.of(2022, 10, 25, 14, 0),
                LocalDateTime.of(2022,11,25,19,30),
                LocalDateTime.of(2023,2,7,19,30),
                LocalDateTime.of(2023, 2, 6, 0,0 ),
                "100분",
                "전체 관람가",
                StageType.PLAY,
                "포스터 링크",
                "상세 포스터 링크",
                "공연 상세 설명"
        );
        stageRepository.save(stage1);
        stageRepository.save(stage2);

        // when
        Page<ResponseStageThumbnailDto> resultPage = stageService
                                                    .getOngoingList(PageRequest.of(0, 6,
                                                            Sort.by(Sort.Order.desc("createAt"))));

        // then
        List<ResponseStageThumbnailDto> resultContent = resultPage.getContent();
        assertEquals(3, resultContent.size());
        assertTrue(resultContent.stream().anyMatch(dto -> dto.getName().equals("뮤지컬<펀홈>")));
        assertTrue(resultContent.stream().anyMatch(dto -> dto.getName().equals("청소년극<#버킷리스트>")));
        assertFalse(resultContent.stream().anyMatch(dto -> dto.getName().equals("청소년극<발가락육상천재>")));

        resultContent.forEach(dto -> System.out.println("현재 진행 중인 무대: " + dto.getName() +
                                                        ", 시작 시간: " + dto.getStageOpen()));
    }

    @Test
    @Transactional
    void StageDetail() {
        // given
        Stage savedStage = Stage.createStage(
                "뮤지컬<마리퀴리>",
                "홍익대 아트센터 대극장",
                LocalDateTime.of(2023, 10, 25, 14, 0),
                LocalDateTime.of(2023,11,25,19,30),
                LocalDateTime.of(2024,2,7,19,30),
                LocalDateTime.of(2024, 2, 6, 0,0 ),
                "180분",
                "8세이상 관람가",
                StageType.MUSICAL,
                "포스터 링크",
                "상세 포스터 링크",
                "공연 상세 설명"
        );
        stageRepository.save(savedStage);
        UUID id = savedStage.getId();

        // when
        ResponseStageDto findResponseStageDto = stageService.stageDetail(id);

        //then
        assertNotNull(findResponseStageDto);
        assertEquals("뮤지컬<마리퀴리>", findResponseStageDto.getName());
        assertEquals("홍익대 아트센터 대극장", findResponseStageDto.getPlace());
        assertEquals(LocalDateTime.of(2023, 10, 25, 14, 0),
                                        findResponseStageDto.getTicketOpen());
        assertEquals(LocalDateTime.of(2023, 11, 25, 19, 30),
                                        findResponseStageDto.getStageOpen());
        assertEquals(LocalDateTime.of(2024,2,7,19,30),
                                        findResponseStageDto.getStageClose());
        assertEquals(LocalDateTime.of(2024, 2, 6, 0, 0),
                                        findResponseStageDto.getLastTicket());
        assertEquals("180분", findResponseStageDto.getRunningTime());
        assertEquals("8세이상 관람가", findResponseStageDto.getAgeLimit());
        assertEquals(StageType.MUSICAL, findResponseStageDto.getStageType());
        assertEquals("포스터 링크", findResponseStageDto.getPosterImg());
        assertEquals("상세 포스터 링크", findResponseStageDto.getDetailPosterImg());
        assertEquals("공연 상세 설명", findResponseStageDto.getStageInfo());
    }

    @Test
    @Transactional
    void getStageTypeList() {
        //given
        Stage stage1 = Stage.createStage(
                "뮤지컬<펀홈>",
                "홍익대 아트센터 대극장",
                LocalDateTime.of(2023, 10, 25, 14, 0),
                LocalDateTime.of(2023,11,25,19,30),
                LocalDateTime.of(2024,2,7,19,30),
                LocalDateTime.of(2024, 2, 6, 0,0 ),
                "180분",
                "8세이상 관람가",
                StageType.MUSICAL,
                "포스터 링크",
                "상세 포스터 링크",
                "공연 상세 설명"
        );
        stageRepository.save(stage1);

        Stage stage2 = Stage.createStage(
                "청소년극<발가락육상천재>",
                "국립극단 소극장 판",
                LocalDateTime.of(2023, 10, 25, 14, 0),
                LocalDateTime.of(2023,11,25,19,30),
                LocalDateTime.of(2024,2,7,19,30),
                LocalDateTime.of(2024, 2, 6, 0,0 ),
                "100분",
                "전체 관람가",
                StageType.PLAY,
                "포스터 링크",
                "상세 포스터 링크",
                "공연 상세 설명"
        );
        stageRepository.save(stage2);

        //when
        Page<ResponseStageThumbnailDto> resultPage = stageService.getStageTypeList(StageType.PLAY,
                                                                PageRequest.of(0, 6,
                                                                Sort.by(Sort.Order.desc("createAt"))));
        //then
        List<ResponseStageThumbnailDto> stageTypeList = resultPage.getContent();
        assertEquals(3, stageTypeList.size());
        assertFalse(stageTypeList.stream().allMatch(dto -> dto.getName().equals("뮤지컬<펀홈>")));
        assertThat(stageTypeList, Matchers.hasItem(hasProperty("name", equalTo("청소년극<발가락육상천재>"))));
//        assertTrue(stageTypeList.stream().allMatch(dto -> dto.getName().equals("청소년극<발가락육상천재>")));
//        assertTrue(stageTypeList.stream().allMatch(dto -> dto.getName().equals("청소년극<#버킷리스트>")));
    }

    @Test
    @Transactional
    void getComingSoonList() {
        //given
        Stage stage1 = Stage.createStage(
                "뮤지컬<라이온킹>",
                "홍익대 아트센터 대극장",
                LocalDateTime.of(2024, 10, 25, 14, 0),
                LocalDateTime.of(2024, 11, 25, 19, 30),
                LocalDateTime.of(2025, 2, 7, 19, 30),
                LocalDateTime.of(2025, 2, 6, 0, 0),
                "180분",
                "8세이상 관람가",
                StageType.MUSICAL,
                "포스터 링크",
                "상세 포스터 링크",
                "공연 상세 설명"
        );
        stageRepository.save(stage1);

        Stage stage2 = Stage.createStage(
                "청소년극<발가락육상천재>",
                "국립극단 소극장 판",
                LocalDateTime.of(2024, 10, 25, 14, 0),
                LocalDateTime.of(2024, 11, 25, 19, 30),
                LocalDateTime.of(2025, 2, 7, 19, 30),
                LocalDateTime.of(2025, 2, 6, 0, 0),
                "100분",
                "전체 관람가",
                StageType.PLAY,
                "포스터 링크",
                "상세 포스터 링크",
                "공연 상세 설명"
        );
        stageRepository.save(stage2);

        Page<ResponseStageThumbnailDto> resultList = stageService
                                                    .getComingSoonList(PageRequest.of(0, 6,
                                                    Sort.by(Sort.Order.desc("createAt"))));

        List<ResponseStageThumbnailDto> comingSoonList = resultList.getContent();
        assertEquals(4, comingSoonList.size());
        assertTrue(comingSoonList.stream().anyMatch(dto -> dto.getName().equals("뮤지컬<라이온킹>")));
        assertTrue(comingSoonList.stream().anyMatch(dto -> dto.getName().equals("청소년극<발가락육상천재>")));
        assertTrue(comingSoonList.stream().anyMatch(dto -> dto.getName().equals("하현상 콘서트<With All My Heart>")));
        assertFalse(comingSoonList.stream().anyMatch(dto -> dto.getName().equals("청소년극<#버킷리스트>")));
        assertFalse(comingSoonList.stream().anyMatch(dto -> dto.getName().equals("뮤지컬<마리퀴리>")));

        comingSoonList.forEach(dto -> System.out.println("티켓 오픈 예정인 무대: " + dto.getName() +
                ", 시작 시간: " + dto.getStageOpen()));
    }

    @Test
    @Transactional
    void getEndedList() {
        //given
        Stage stage1 = Stage.createStage(
                "뮤지컬<라이온킹>",
                "홍익대 아트센터 대극장",
                LocalDateTime.of(2022, 10, 25, 14, 0),
                LocalDateTime.of(2022, 11, 25, 19, 30),
                LocalDateTime.of(2023, 2, 7, 19, 30),
                LocalDateTime.of(2023, 2, 6, 0, 0),
                "180분",
                "8세이상 관람가",
                StageType.MUSICAL,
                "포스터 링크",
                "상세 포스터 링크",
                "공연 상세 설명"
        );
        stageRepository.save(stage1);

        Stage stage2 = Stage.createStage(
                "청소년극<발가락육상천재>",
                "국립극단 소극장 판",
                LocalDateTime.of(2022, 10, 25, 14, 0),
                LocalDateTime.of(2022, 11, 25, 19, 30),
                LocalDateTime.of(2023, 2, 7, 19, 30),
                LocalDateTime.of(2023, 2, 6, 0, 0),
                "100분",
                "전체 관람가",
                StageType.PLAY,
                "포스터 링크",
                "상세 포스터 링크",
                "공연 상세 설명"
        );
        stageRepository.save(stage2);

        Page<ResponseAdminStageDto> resultList = stageService
                                                     .getEndedList(PageRequest.of(0, 6,
                                                     Sort.by(Sort.Order.desc("createAt"))));

        List<ResponseAdminStageDto> endedList = resultList.getContent();
        assertEquals(3, endedList.size());
        assertTrue(endedList.stream().anyMatch(dto -> dto.getName().equals("뮤지컬<라이온킹>")));
        assertTrue(endedList.stream().anyMatch(dto -> dto.getName().equals("청소년극<발가락육상천재>")));
        assertTrue(endedList.stream().anyMatch(dto -> dto.getName().equals("뮤지컬<마리퀴리>")));
        assertFalse(endedList.stream().anyMatch(dto -> dto.getName().equals("청소년극<#버킷리스트>")));
        assertFalse(endedList.stream().anyMatch(dto -> dto.getName().equals("하현상 콘서트<With All My Heart>")));

        endedList.forEach(dto -> System.out.println("종료된 무대: " + dto.getName() +
                ", 시작 시간: " + dto.getStageOpen()));
    }

    @Test
    @Transactional
    void updateInfo() {
        //given
        String newName = "청소년극<#버킷리스트>n";
        String newPlace = "국립극단 소극장 판";
        LocalDateTime newTicketOpen = LocalDateTime.of(2023, 10, 25, 14, 0);
        LocalDateTime newOpen = LocalDateTime.of(2023, 11, 25, 19, 30);
        LocalDateTime newClose = LocalDateTime.of(2024, 2, 7, 19, 30);
        LocalDateTime newLastTicket = LocalDateTime.of(2024,2,6,0,0);
        String newRunningTime = "100분";
        String newAgeLimit = "전체 관람가";
        StageType newType = StageType.PLAY;
        String newPoster = "포스터 링크";
        String newDetailPoster = "상세 포스터 링크";
        String newInfo = "공연 상세 설명";

        Stage createStage = Stage.createStage(
                "뮤지컬<마리퀴리>",
                "홍익대 아트센터 대극장",
                LocalDateTime.of(2023, 10, 25, 14, 0),
                LocalDateTime.of(2023,11,25,19,30),
                LocalDateTime.of(2024,2,7,19,30),
                LocalDateTime.of(2024, 2, 6, 0,0 ),
                "180분",
                "8세이상 관람가",
                StageType.MUSICAL,
                "포스터 링크",
                "상세 포스터 링크",
                "공연 상세 설명"
        );
        Stage savedStage = stageRepository.save(createStage);
        UUID id = savedStage.getId();

        RequestUpdateInfoDto.RequestUpdateInfoDtoBuilder update = RequestUpdateInfoDto.builder()
                .name(newName)
                .place(newPlace)
                .ticketOpen(newTicketOpen)
                .stageOpen(newOpen)
                .stageClose(newClose)
                .lastTicket(newLastTicket)
                .runningTime(newRunningTime)
                .ageLimit(newAgeLimit)
                .stageType(newType)
                .posterImg(newPoster)
                .detailPosterImg(newDetailPoster)
                .stageInfo(newInfo);

        //when
        stageService.updateInfo(id, update.build());

        //then
        Stage updatedStage = stageRepository.findById(id)
                .orElseThrow(() -> new AssertionError("업데이트된 정보가 없습니다."));

        assertEquals(newName, updatedStage.getName());
    }

    @Test
    @Transactional
    void deleteStage() {
        //given
        Stage savedStage =Stage.createStage(
                "뮤지컬<마리퀴리>",
                "홍익대 아트센터 대극장",
                LocalDateTime.of(2023, 10, 25, 14, 0),
                LocalDateTime.of(2023,11,25,19,30),
                LocalDateTime.of(2024,2,7,19,30),
                LocalDateTime.of(2024, 2, 6, 0,0 ),
                "180분",
                "8세이상 관람가",
                StageType.MUSICAL,
                "포스터 링크",
                "상세 포스터 링크",
                "공연 상세 설명"
        );

        stageRepository.save(savedStage);
        UUID id = savedStage.getId();

        RequestDeleteStageDto deleteStageDto = new RequestDeleteStageDto();

        //when
        stageService.deleteStage(id);

        //then
        assertThrows(EntityNotFoundException.class,
                () -> stageRepository.findById(id).orElseThrow(EntityNotFoundException::new));
    }
}