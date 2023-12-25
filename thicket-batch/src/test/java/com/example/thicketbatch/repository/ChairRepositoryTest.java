package com.example.thicketbatch.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ChairRepositoryTest {
    @Autowired
    ChairRepository repository;

    @Test
    void findAvailableCountByUuid() {
        int count =repository.findCountByChairId(UUID.fromString("5b3a69b0-a644-47f5-bd20-16cd40c547a4"));
        Assertions.assertEquals(count,300);
    }

    @Test
    void updateAvailableCountByUuid() {
    }
}