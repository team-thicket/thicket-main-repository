package com.example.thicketstage.repository;

import com.example.thicketstage.domain.Chair;
import com.example.thicketstage.domain.StageStart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ChairRepository extends JpaRepository<Chair, Long> {

    Optional<Chair> findByUuid(String uuid);

    List<Chair> findByStageStart(StageStart stageStart);

}
