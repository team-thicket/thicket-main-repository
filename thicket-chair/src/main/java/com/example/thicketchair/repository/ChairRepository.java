package com.example.thicketchair.repository;

import com.example.thicketchair.domain.Chair;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChairRepository extends JpaRepository<Chair, Long> {

    Chair findChairByUuid(String uuid);
    void deleteChairByUuid(String uuid);
    boolean existsByChairType(String chairType);
}
