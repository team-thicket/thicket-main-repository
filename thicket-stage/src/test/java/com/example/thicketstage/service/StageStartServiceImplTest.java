package com.example.thicketstage.service;

import com.example.thicketstage.domain.Stage;
import com.example.thicketstage.domain.StageStart;
import com.example.thicketstage.dto.request.RequestCreateStageStartDto;
import com.example.thicketstage.dto.response.ResponseStageStartDto;
import com.example.thicketstage.repository.StageRepository;
import com.example.thicketstage.repository.StageStartRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StageStartServiceImplTest {

    @Autowired
    StageRepository stageRepository;
    @Autowired
    StageService stageService;
    @Autowired
    StageStartRepository stageStartRepository;
    @Autowired
    StageStartService stageStartService;

    @Test
    @Transactional
    void createStageStart() {
        // given
        List<Stage> stages = stageRepository.findAll();
        Stage stage = stages.get(0);

        RequestCreateStageStartDto createStageStartDto = new RequestCreateStageStartDto();
        createStageStartDto.setStageUuid(stage.getUuid());

        RequestCreateStageStartDto.StageStartDto stageStartDto = new RequestCreateStageStartDto.StageStartDto();
        stageStartDto.setDate(LocalDate.of(2023, 11, 25));
        stageStartDto.setTime(LocalTime.of(19, 30));

        RequestCreateStageStartDto.StageStartDto stageStartDto1 = new RequestCreateStageStartDto.StageStartDto();
        stageStartDto1.setDate(LocalDate.of(2023, 11, 25));
        stageStartDto1.setTime(LocalTime.of(21, 30));


        createStageStartDto.setStageStartDtos(Arrays.asList(stageStartDto, stageStartDto1));

        // when
        List<StageStart> createdStageStarts = stageStartService.createStageStart(createStageStartDto);

        // then
        assertNotNull(createdStageStarts);

        assertEquals(2, createdStageStarts.size());

        StageStart stageStart = createdStageStarts.get(0);
        assertNotNull(stageStart);
        assertEquals(stageStartDto.getDate(), stageStart.getDate());
        assertEquals(stageStartDto.getTime(), stageStart.getTime());

//        RequestCreateStageStartDto.StageStartDto stageStartDto = new RequestCreateStageStartDto.StageStartDto();
//        stageStartDto.setDate(LocalDate.of(2023, 11, 25));
//        stageStartDto.setTimes(Arrays.asList(LocalTime.of(19, 30),
//                                            LocalTime.of(21, 30)));
//
//        createStageStartDto.setStageStartDtos(Arrays.asList(stageStartDto));
//
//        // when
//        List<StageStart> createdStageStarts = stageStartService.createStageStart(createStageStartDto);
//
//        // then
//        assertNotNull(createdStageStarts);
//
//        assertEquals(1, createdStageStarts.size());
//
//        StageStart stageStart = createdStageStarts.get(0);
//        assertNotNull(stageStart);
//        assertEquals(stageStartDto.getDate(), stageStart.getDate());
//        assertEquals(stageStartDto.getTimes(), stageStart.getTimes());
    }

    @Test
    @Transactional
    void getAllDate() {
        // given
        List<Stage> stages = stageRepository.findAll();
        Stage stage = stages.get(0);

        StageStart stageStart1 = StageStart.createStageStart(LocalDate.of(2023, 11, 25),
                                                            LocalTime.of(19, 30), stage);
        StageStart stageStart2 = StageStart.createStageStart(LocalDate.of(2023, 12, 1),
                                                            LocalTime.of(20, 0), stage);

        stageStartRepository.save(stageStart1);
        stageStartRepository.save(stageStart2);

        // when
        List<ResponseStageStartDto> allDate = stageStartService.getAllDate();

        // then
        assertNotNull(allDate);
        assertEquals(7, allDate.size());
    }

    // 회차 정보 수정은 추후 고도화 구현시 구현 예정
//    @Test
//    @Transactional
//    void updateStageStart() {
//        // given
//        List<Stage> stages = stageRepository.findAll();
//        Stage stage = stages.get(0);
//
//        StageStart stageStart = StageStart.createStageStart(LocalDate.of(2023, 11, 25),
//                                                            Arrays.asList(LocalTime.of(19, 30)), stage);
//        stageStartRepository.save(stageStart);
//
//        RequestStageStartUpdateDto updateDto = new RequestStageStartUpdateDto();
//        updateDto.setDate(LocalDate.of(2023, 12, 1));
//        updateDto.setTimes(Arrays.asList(LocalTime.of(20, 0)));
//
//        // when
//        stageStartService.updateStageStart(stageStart.getId(), updateDto);
//
//        // then
//        StageStart updatedStageStart = stageStartRepository.findById(stageStart.getId()).orElse(null);
//        assertNotNull(updatedStageStart);
//        assertEquals(updateDto.getDate(), updatedStageStart.getDate());
//        assertEquals(updateDto.getTimes(), updatedStageStart.getTimes());
//    }

    @Test
    @Transactional
    void deleteStageStart() {
        // given
        List<Stage> stages = stageRepository.findAll();
        Stage stage = stages.get(0);

        StageStart stageStart = StageStart.createStageStart(LocalDate.of(2023, 11, 25),
                                                            LocalTime.of(19, 30), stage);
        stageStartRepository.save(stageStart);

        // when
        stageStartService.deleteStageStart(stageStart.getUuid());

        // then
        assertFalse(stageStartRepository.findByUuid(stageStart.getUuid()).isPresent());
    }
}