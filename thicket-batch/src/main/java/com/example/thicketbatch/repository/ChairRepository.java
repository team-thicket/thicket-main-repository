package com.example.thicketbatch.repository;


import com.example.thicketbatch.stage.Chair;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ChairRepository extends JpaRepository<Chair, Long> {

    // 좌석의 availableCount 조회
    @Query("SELECT c.availableCount FROM Chair c WHERE c.uuid = :uuid")
    int findAvailableCountByUuid(@Param("uuid") String uuid);

    // 좌석의 availableCount 갱신
    @Query("UPDATE Chair c SET c.availableCount = :availableCount WHERE c.uuid = :uuid")
    void updateAvailableCountByUuid(@Param("uuid") String uuid, @Param("availableCount") int availableCount);


}
