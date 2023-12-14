package com.example.thicketstage.repository;

import com.example.thicketstage.domain.Chair;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ChairRepository extends JpaRepository<Chair, Long> {

    Optional<Chair> findByUuid(String uuid);

}
