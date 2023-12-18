package com.example.thicketmember.repository;

import com.example.thicketmember.domain.Verification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VerificationRepository extends JpaRepository<Verification, String> {

}
