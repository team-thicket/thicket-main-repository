package com.example.thicketstage;

import com.example.thicketstage.domain.Chair;
import com.example.thicketstage.domain.Stage;
import com.example.thicketstage.domain.StageStart;
import com.example.thicketstage.enumerate.StageStatus;
import com.example.thicketstage.enumerate.StageType;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Component
@RequiredArgsConstructor
public class Init {

    private final InitService initService;

    @PostConstruct
    public void init() {
        initService.init();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {

        private final EntityManager em;

        public void init() {

            Stage stage1 = Stage.createStage(
                    "뮤지컬<마리퀴리>",
                    "홍익대 아트센터 대극장",
                    LocalDateTime.of(2023, 10, 25, 14, 0),
                    LocalDateTime.of(2023, 11, 25, 19, 30),
                    LocalDateTime.of(2024, 2, 7, 19, 30),
                    LocalDateTime.of(2024, 2, 6, 0, 0),
                    "180분",
                    "8세이상 관람가",
                    StageType.MUSICAL,
                    StageStatus.BEFORE,
                    "포스터 링크",
                    "상세 포스터 링크",
                    "공연 상세 설명"
            );
            em.persist(stage1);

            StageStart stageStart1 = initStageStart(stage1, LocalDate.of(2023, 11, 25),
                                                            LocalTime.of(14, 30));
            StageStart stageStart2 = initStageStart(stage1, LocalDate.of(2023, 11, 25),
                                                            LocalTime.of(19, 30));
            StageStart stageStart3 = initStageStart(stage1, LocalDate.of(2023, 11, 27),
                                                            LocalTime.of(14, 30));

            chairInit(stageStart1, "VIP", 100, 99000);
            chairInit(stageStart1, "R", 200, 88000);
            chairInit(stageStart1, "S", 300, 77000);
            chairInit(stageStart2, "VIP", 100, 99000);
            chairInit(stageStart2, "R", 200, 88000);
            chairInit(stageStart2, "S", 300, 77000);
            chairInit(stageStart3, "VIP", 100, 99000);
            chairInit(stageStart3, "R", 200, 88000);
            chairInit(stageStart3, "S", 300, 77000);

            Stage stage2 = Stage.createStage(
                    "청소년극<#버킷리스트>",
                    "국립극단 소극장 판",
                    LocalDateTime.of(2023, 10, 25, 14, 0),
                    LocalDateTime.of(2023, 11, 25, 19, 30),
                    LocalDateTime.of(2024, 2, 7, 19, 30),
                    LocalDateTime.of(2024, 2, 6, 0, 0),
                    "100분",
                    "전체 관람가",
                    StageType.PLAY,
                    StageStatus.ENDED,
                    "포스터 링크",
                    "상세 포스터 링크",
                    "공연 상세 설명"
            );
            em.persist(stage2);

            StageStart stageStart4 = initStageStart(stage2, LocalDate.of(2023, 11, 25),
                                                            LocalTime.of(14, 30));
            StageStart stageStart5 = initStageStart(stage2, LocalDate.of(2023, 11, 25),
                                                            LocalTime.of(19, 30));
            chairInit(stageStart4, "VIP", 200, 99000);
            chairInit(stageStart5, "VIP", 200, 99000);
        }

        private StageStart initStageStart(Stage stage, LocalDate date, LocalTime time) {
            StageStart stageStart = StageStart.createStageStart(date, time, stage);
            em.persist(stageStart);
            return stageStart;
        }

        public void chairInit(StageStart stageStart, String chairType, int count, int price) {
            Chair chair = Chair.createChair(chairType, count, price, stageStart);
            em.persist(chair);
        }
    }
}