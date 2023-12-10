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

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StageStart extends TimeStamp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;

    @ElementCollection
    private List<LocalTime> times = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    private Stage stage;

//    // 연관관계 메서드 //
    public void foreignKey(Stage stage){
        this.stage = stage;
        stage.getStageStart().add(this);
    }

    // 비즈니스 로직 //
    public static StageStart createStageStart(LocalDate newDate, List<LocalTime> newTime, Stage stage) {
        StageStart stageStart = new StageStart();
        stageStart.date = newDate;
        stageStart.times.addAll(newTime);
        stageStart.foreignKey(stage);

        return stageStart;
    }

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
            stage = null;
        }
    }
}
