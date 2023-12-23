package com.example.thicketbatch.repository;

import com.example.thicketbatch.stage.Stage;
import com.example.thicketbatch.stage.StageStart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StageStartRepository extends JpaRepository<StageStart, Long> {
    Optional<StageStart> findByUuid(String uuid);

    List<StageStart> findByStage(Stage stage);


}