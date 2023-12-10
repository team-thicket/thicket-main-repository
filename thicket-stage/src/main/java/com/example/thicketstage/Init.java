package com.example.thicketstage;

import com.example.thicketstage.domain.Stage;
import com.example.thicketstage.enumerate.StageStatus;
import com.example.thicketstage.enumerate.StageType;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

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
//            List<LocalTime> times = new ArrayList<>(List.of(LocalTime.of(14, 30),
//                                                            LocalTime.of(19, 30)));
//
//            RequestCreateStageStartDto stageStart1 = new RequestCreateStageStartDto();
//            stageStart1.setDate(LocalDate.of(2023, 11, 30));
//            stageStart1.setTimes(times);
//
//            RequestCreateStageStartDto stageStart2 = new RequestCreateStageStartDto();
//            stageStart2.setDate(LocalDate.of(2023, 12, 1));
//            stageStart2.setTimes(times);

            Stage stage1 = Stage.createStage(
                    "뮤지컬<마리퀴리>",
                    "홍익대 아트센터 대극장",
                    LocalDateTime.of(2023,11,25,19,30),
                    LocalDateTime.of(2024,2,7,19,30),
                    "180분",
                    "8세이상 관람가",
                    StageType.MUSICAL,
                    StageStatus.BEFORE,
//                    List.of(stageStart1,stageStart2),
                    "포스터 링크",
                    "상세 포스터 링크",
                    "공연 상세 설명"
            );
            em.persist(stage1);

//            List<LocalTime> times1 = new ArrayList<>(List.of(LocalTime.of(19, 30)));
//
//            RequestCreateStageStartDto stageStart3 = new RequestCreateStageStartDto();
//            stageStart3.setDate(LocalDate.of(2023, 12, 5));
//            stageStart3.setTimes(times1);
//
//            RequestCreateStageStartDto stageStart4 = new RequestCreateStageStartDto();
//            stageStart4.setDate(LocalDate.of(2023, 12, 30));
//            stageStart4.setTimes(times);

            Stage stage2 = Stage.createStage(
                    "청소년극<#버킷리스트>",
                    "국립극단 소극장 판",
                    LocalDateTime.of(2023,11,25,19,30),
                    LocalDateTime.of(2024,2,7,19,30),
                    "100분",
                    "전체 관람가",
                    StageType.PLAY,
                    StageStatus.ENDED,
//                    List.of(stageStart3,stageStart4),
                    "포스터 링크",
                    "상세 포스터 링크",
                    "공연 상세 설명"
            );
            em.persist(stage2);
        }
    }
}