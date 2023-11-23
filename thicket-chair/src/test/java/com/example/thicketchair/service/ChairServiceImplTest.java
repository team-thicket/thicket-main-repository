package com.example.thicketchair.service;

import com.example.thicketchair.domain.Chair;
import com.example.thicketchair.dto.request.RequestCreateChairDto;
import com.example.thicketchair.dto.request.RequestUpdateChairDto;
import com.example.thicketchair.dto.response.ResponseChairDto;
import com.example.thicketchair.repository.ChairRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback(value = false)
class ChairServiceImplTest {
    @Autowired
    ChairService chairService;
    @Autowired
    ChairRepository chairRepository;

    @Test
    void 좌석_생성() { // 한꺼번에 실행하면 데이터 쌓여서 실패, 단일 실행시에만 성공
        // Given
        RequestCreateChairDto createChairDto = new RequestCreateChairDto();
        createChairDto.setChairType("Standard");
        createChairDto.setCount(50);
        createChairDto.setPrice(200_000);

        // When
        chairService.createChair(createChairDto);

        // Then
        List<Chair> chairs = chairRepository.findAll();
        assertEquals(2, chairs.size()); // Init 때문에 size +1

        Chair savedChair = chairs.get(1);
        assertEquals(createChairDto.getChairType(), savedChair.getChairType());
        assertEquals(createChairDto.getCount(), savedChair.getCount());
        assertEquals(createChairDto.getPrice(), savedChair.getPrice());
    }

    @Test
    void 단일_좌석_조회() {
        // Given
        List<Chair> findChairs = chairRepository.findAll();
        String uuid = findChairs.get(0).getUuid();

        // When
        ResponseChairDto responseChairDto = chairService.findChairByByUuid(uuid);

        // Then
        assertEquals("VIP", responseChairDto.getChairType());
        assertEquals(100, responseChairDto.getCount());
        assertEquals(189_000, responseChairDto.getPrice());
    }

    @Test
    void 전체_좌석_조회() {
        // Given
        // Create test data and persist it to the database
        Chair testChair1 = Chair.createChair("VIP",100, 189_000);
        Chair testChair2 = Chair.createChair("Standard", 150, 200_000);
        chairRepository.saveAll(List.of(testChair1, testChair2));

        // When
        List<ResponseChairDto> result = chairService.getAllChairs();

        // Then
        // Check if the result is not null and has the correct number of elements
        assertNotNull(result);
        assertEquals(3, result.size()); // // Init 때문에 size +1

        // Check if the attributes of the first chair in the result match the test data
        ResponseChairDto resultChair1 = result.get(1);
        assertEquals(testChair1.getChairType(), resultChair1.getChairType());
        assertEquals(testChair1.getCount(), resultChair1.getCount());
        assertEquals(testChair1.getPrice(), resultChair1.getPrice());

        // Check if the attributes of the second chair in the result match the test data
        ResponseChairDto resultChair2 = result.get(2);
        assertEquals(testChair2.getChairType(), resultChair2.getChairType());
        assertEquals(testChair2.getCount(), resultChair2.getCount());
        assertEquals(testChair2.getPrice(), resultChair2.getPrice());
    }

    @Test
    void 좌석_수정() {
        // Given
        List<Chair> findChairs = chairRepository.findAll();
        String uuid = findChairs.get(0).getUuid();

        RequestUpdateChairDto dto = new RequestUpdateChairDto();

        dto.setChairType("String");
        dto.setCount(10);
        dto.setPrice(5000);

        // When
        chairService.updateChair(uuid, dto);
        Chair findChair = chairRepository.findChairByUuid(uuid);

        // Then
        assertEquals("String", findChair.getChairType());
        assertEquals(10, findChair.getCount());
        assertEquals(5000, findChair.getPrice());
    }

    @Test
    void 좌석_삭제() {
        // Given
        List<Chair> findChairsBeforeDeletion = chairRepository.findAll();
        String uuid = findChairsBeforeDeletion.get(0).getUuid();

        // When
        chairService.deleteChair(uuid);

        // Then
        List<Chair> findChairsAfterDeletion = chairRepository.findAll();

        // 1. Check that the size of the list decreased by 1 after deletion
        assertEquals(findChairsBeforeDeletion.size() - 1, findChairsAfterDeletion.size());

        // 2. Check that the chair with the specified UUID does not exist after deletion
        assertTrue(findChairsAfterDeletion.stream().noneMatch(chair -> chair.getUuid().equals(uuid)));
    }
}
