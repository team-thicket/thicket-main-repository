package com.example.thicketstage.repository;

import com.example.thicketstage.domain.Stage;
import com.example.thicketstage.enumerate.StageType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StageRepository extends JpaRepository<Stage, Long> {

//    @Query("SELECT s FROM Stage s WHERE s.name LIKE %:name%")
//    List<Stage> findByNameContaining(@Param("name") String name);
//    @Query("SELECT s FROM Stage s WHERE s.place LIKE %:place%")
//    List<Stage> findByPlaceContaining(@Param("place") String place);

    @Query("SELECT s FROM Stage s WHERE s.name LIKE :keyword OR s.place LIKE :keyword")
    List<Stage> searchByNameOrPlace(@Param("keyword") String keyword);

    List<Stage> findByStageType(StageType stageType);
}
