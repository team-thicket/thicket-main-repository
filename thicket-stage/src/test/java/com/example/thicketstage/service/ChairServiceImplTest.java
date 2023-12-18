package com.example.thicketstage.service;

import com.example.thicketstage.domain.Chair;
import com.example.thicketstage.domain.StageStart;
import com.example.thicketstage.dto.request.RequestCreateChairDto;
import com.example.thicketstage.dto.request.RequestUpdateChairDto;
import com.example.thicketstage.dto.response.ResponseChairDto;
import com.example.thicketstage.repository.ChairRepository;
import com.example.thicketstage.repository.StageStartRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ChairServiceImplTest {

    @Autowired
    StageStartRepository stageStartRepository;
    @Autowired
    StageStartService stageStartService;
    @Autowired
    ChairRepository chairRepository;
    @Autowired
    ChairService chairService;

    @Test
    @Transactional
    void createChair() {
        //given
        List<StageStart> stageStarts = stageStartRepository.findAll();
        StageStart stageStart = stageStarts.get(0);

        RequestCreateChairDto createChairDto = new RequestCreateChairDto();
        createChairDto.setStageStartUuid(stageStart.getUuid());

        RequestCreateChairDto.ChairDto chairDto = new RequestCreateChairDto.ChairDto();
        chairDto.setChairType("VIP");
        chairDto.setCount(100);
        chairDto.setPrice(99000);

        createChairDto.setChairDtos(Arrays.asList(chairDto));

        //when
        List<Chair> createdChair = chairService.createChair(createChairDto);

        //then
        assertNotNull(createdChair);
        assertEquals(1, createdChair.size());

        Chair chair = createdChair.get(0);
        assertNotNull(chair);
        assertEquals(chairDto.getChairType(), chair.getChairType());
        assertEquals(chairDto.getCount(), chair.getCount());
        assertEquals(chairDto.getPrice(), chair.getPrice());
    }

    @Test
    @Transactional
    void findChairByUuid() {
        // given
        List<StageStart> stageStarts = stageStartRepository.findAll();
        StageStart stageStart = stageStarts.get(0);

        Chair chair = Chair.createChair("VIP", 100, 99000, stageStart);
        chairRepository.save(chair);

        // when
        ResponseChairDto responseChairDto = chairService.findChairByUuid(chair.getUuid());

        // then
        assertNotNull(responseChairDto);
        assertEquals(chair.getUuid(), responseChairDto.getStageStartUuid()
        );
        assertEquals(chair.getChairType(), responseChairDto.getChairType());
        assertEquals(chair.getCount(), responseChairDto.getCount());
        assertEquals(chair.getPrice(), responseChairDto.getPrice());
    }

    @Test
    @Transactional
    void getAllChair() {
        // given
        List<StageStart> stageStarts = stageStartRepository.findAll();
        StageStart stageStart = stageStarts.get(0);

        Chair chair1 = Chair.createChair("VIP", 100, 99000, stageStart);
        Chair chair2 = Chair.createChair("R", 200, 88000, stageStart);

        chairRepository.save(chair1);
        chairRepository.save(chair2);

        // when
        List<ResponseChairDto> allChair = chairService.getAllChair();

        //then
        assertNotNull(allChair);
        assertEquals(13, allChair.size());
    }

    @Test
    @Transactional
    void updateChair() {
        // given
        List<StageStart> stageStarts = stageStartRepository.findAll();
        StageStart stageStart = stageStarts.get(0);

        Chair chair = Chair.createChair("VIP", 100, 99000, stageStart);
        chairRepository.save(chair);

        RequestUpdateChairDto updateDto = new RequestUpdateChairDto();
        RequestUpdateChairDto.UpdateChairDto updateChairDto = new RequestUpdateChairDto.UpdateChairDto();
        updateChairDto.setChairType("R");
        updateChairDto.setCount(200);
        updateChairDto.setPrice(88000);
        updateDto.setUpdateChairDtos(Collections.singletonList(updateChairDto));

        // when
        chairService.updateChair(chair.getUuid(), updateDto);
        chairRepository.save(chair);

        //then
        Chair updatedChair = chairRepository.findByUuid(chair.getUuid()).orElse(null);
        assertNotNull(updatedChair);
        assertEquals(updateChairDto.getChairType(), updatedChair.getChairType());
        assertEquals(updateChairDto.getCount(), updatedChair.getCount());
        assertEquals(updateChairDto.getPrice(), updatedChair.getPrice());
    }

    @Test
    @Transactional
    void deleteChair() {
        //given
        List<StageStart> stageStarts = stageStartRepository.findAll();
        StageStart stageStart = stageStarts.get(0);

        Chair chair = Chair.createChair("VIP", 100, 99000, stageStart);
        chairRepository.save(chair);

        // when
        chairService.deleteChair(chair.getUuid());

        // then
        assertFalse(chairRepository.findByUuid(chair.getUuid()).isPresent());
    }
}