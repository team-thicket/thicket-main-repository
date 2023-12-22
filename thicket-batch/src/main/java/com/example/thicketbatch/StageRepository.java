package com.example.thicketbatch;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class StageRepository {

    private final JdbcTemplate jdbcTemplate;


    public int getAvailableCount(String uuid) {
        String sql = "SELECT available_count FROM chair WHERE stage_start_id = (SELECT id FROM stage_start WHERE uuid = ?)";
        return jdbcTemplate.queryForObject(sql, Integer.class, uuid);
    }

    public void updateAvailableCount(String uuid, int newAvailableCount) {
        String sql = "UPDATE chair SET available_count = ? WHERE stage_start_id = (SELECT id FROM stage_start WHERE uuid = ?)";
        jdbcTemplate.update(sql, newAvailableCount, uuid);
    }
}