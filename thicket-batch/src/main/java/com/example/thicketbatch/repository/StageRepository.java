package com.example.thicketbatch.repository;


import com.example.thicketbatch.enumerate.StageType;
import com.example.thicketbatch.stage.Stage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface StageRepository extends JpaRepository<Stage, Long> {

    @Query("SELECT s FROM Stage s WHERE s.name LIKE :keyword OR s.place LIKE :keyword")
    List<Stage> searchByNameOrPlace(@Param("keyword") String keyword);

    List<Stage> findByStageType(StageType stageType);

    Optional<Stage> findByUuid(String uuid);

}