package com.example.thicketbatch.repository;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class TestRepositoryImpl implements TestRepository{
    private final EntityManager em;
    @Override
    public Integer findChair(UUID chairID) {
        em.createQuery("SELECT c.availableCount FROM Chair c WHERE c.id = :chairId")
                .setParameter("chairId", chairID)
                .getFirstResult();
        return null;
    }
}
