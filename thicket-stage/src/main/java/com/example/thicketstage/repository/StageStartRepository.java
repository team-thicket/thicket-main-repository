package com.example.thicketstage.repository;

import com.example.thicketstage.domain.Stage;
import com.example.thicketstage.domain.StageStart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StageStartRepository extends JpaRepository<StageStart, Long>  {
    Optional<StageStart> findByUuid(String uuid);

    List<StageStart> findByStage(Stage stage);


}