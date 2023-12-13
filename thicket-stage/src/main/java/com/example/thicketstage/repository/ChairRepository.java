package com.example.thicketstage.repository;

import com.example.thicketstage.domain.Chair;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChairRepository extends JpaRepository<Chair, Long> {

    Chair findChairByUuid(String uuid);
    void deleteChairByUuid(String uuid);
    boolean existsByStageUuidAndChairType(String stageUuid, String chairType);
}
