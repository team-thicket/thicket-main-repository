package com.example.thicketstage.domain;

import com.example.thicketstage.TimeStamp;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StageStart extends TimeStamp {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(length = 100, nullable = false)
    private LocalDate date;

    @Column(length = 100, nullable = false)
    private LocalTime time;

    @ManyToOne(fetch = FetchType.LAZY)
    private Stage stage;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "stageStart", cascade = CascadeType.REMOVE)
    private List<Chair> chair = new ArrayList<>();


//    // 연관관계 메서드 //
    public void foreignKey(Stage stage){
        this.stage = stage;
        stage.getStageStart().add(this);
    }

    // 비즈니스 로직 //
    public static StageStart createStageStart(LocalDate newDate,
                                              LocalTime newTime,
                                              Stage stage) {
        StageStart stageStart = new StageStart();
        stageStart.date = newDate;
        stageStart.time = newTime;
        stageStart.foreignKey(stage);

        return stageStart;
    }
    // 회차 정보 수정은 추후 고도화 구현시 구현 예정
//    public void updateStageStart(RequestStageStartUpdateDto stageStartUpdateDto) {
//        for (RequestStageStartUpdateDto.StageStartUpdateDto startDto : stageStartUpdateDto.getStageStartUpdateDtos()) {
//            StageStart stageStart = new StageStart();
//            stageStart.date = startDto.getDate();
//            stageStart.times = startDto.getTimes();
//            stageStart.stage = this.stage;
//            this.foreignKey(this.stage);
//            this.stage.getStageStart().add(stageStart);
//        }
//    }

    public void deleteStageStart() {
        if(stage != null) {
            stage.getStageStart().remove(this);
        }
    }
}
