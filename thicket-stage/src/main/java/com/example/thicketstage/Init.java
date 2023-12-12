package com.example.thicketstage;

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
import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class Init {

    private final InitService initService;

    @PostConstruct
    public void init(){
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
                    LocalDateTime.of(2023,11,25,19,30),
                    LocalDateTime.of(2024,2,7,19,30),
                    "180분",
                    "8세이상 관람가",
                    StageType.MUSICAL,
                    StageStatus.BEFORE,
                    "포스터 링크",
                    "상세 포스터 링크",
                    "공연 상세 설명"
            );
            em.persist(stage1);

            initStageStart(stage1, LocalDate.of(2023, 11, 25),
                                    Arrays.asList(LocalTime.of(19, 30),
                                                   LocalTime.of(21, 30)));
            initStageStart(stage1, LocalDate.of(2023, 11, 27),
                                    Arrays.asList(LocalTime.of(14, 30)));

            Stage stage2 = Stage.createStage(
                    "청소년극<#버킷리스트>",
                    "국립극단 소극장 판",
                    LocalDateTime.of(2023,11,25,19,30),
                    LocalDateTime.of(2024,2,7,19,30),
                    "100분",
                    "전체 관람가",
                    StageType.PLAY,
                    StageStatus.ENDED,
                    "포스터 링크",
                    "상세 포스터 링크",
                    "공연 상세 설명"
            );
            em.persist(stage2);

            initStageStart(stage2, LocalDate.of(2023, 11, 25),
                                      Arrays.asList(LocalTime.of(19, 30),
                                                    LocalTime.of(21, 30)));
        }

        private void initStageStart(Stage stage, LocalDate date, List<LocalTime> times) {
            StageStart stageStart = StageStart.createStageStart(date, times, stage);
            em.persist(stageStart);
        }
    }
}