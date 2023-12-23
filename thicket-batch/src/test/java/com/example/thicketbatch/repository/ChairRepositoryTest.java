package com.example.thicketbatch.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ChairRepositoryTest {
    @Autowired
    ChairRepository repository;

    @Test
    void findAvailableCountByUuid() {
        int count = repository.findAvailableCountByUuid("1e785eb9-1927-4674-b1eb-14eca84f5e8b");
        Assertions.assertEquals(count,500);
    }

    @Test
    void updateAvailableCountByUuid() {
    }
}