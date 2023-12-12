package com.example.thicketstage.repository;

import com.example.thicketstage.domain.Stage;
import com.example.thicketstage.enumerate.StageStatus;
import com.example.thicketstage.enumerate.StageType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface StageRepository extends JpaRepository<Stage, Long> {

//    Page<Stage> findByStageStatusOrderByCreateAtDesc(StageStatus stageStatus, Pageable pageable);

    @Query("SELECT s FROM Stage s WHERE s.name LIKE :keyword OR s.place LIKE :keyword")
    List<Stage> searchByNameOrPlace(@Param("keyword") String keyword);

    List<Stage> findByStageType(StageType stageType);
//    List<Stage> findByStageTypeOrderByCreateAtDesc(StageType stageType);

    Optional<Stage> findByUuid(String uuid);

    List<Stage> findByStageStatus(StageStatus stageStatus);

}