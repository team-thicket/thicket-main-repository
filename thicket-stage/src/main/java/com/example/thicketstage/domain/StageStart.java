package com.example.thicketstage.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StageStart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;

    @ElementCollection
    private List<LocalTime> times = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    private Stage stage;

    // 연관관계 메서드 //
    public void foreignKey(Stage stage){
        this.stage = stage;
        stage.getStageStart().add(this);
    }

    // 비즈니스 로직 //
//    public static StageStart newStageStart(Stage stage, LocalDate date, List<LocalTime> time) {
//        StageStart stageStart = new StageStart();
//
//        stageStart.foreignKey(stage);
//        stageStart.date = date;
//        stageStart.times.addAll(time);
//
//        return stageStart;
//    }

    public static StageStart createStageStart(Stage stage, LocalDate newDate, List<LocalTime> newTime){
        StageStart stageStart = new StageStart();

        stageStart.foreignKey(stage);
        stageStart.date = newDate;
        ArrayList<LocalTime> newTimes = new ArrayList<>(newTime);
        stageStart.times.addAll(newTimes);

        return stageStart;
    }

}
