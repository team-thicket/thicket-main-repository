package com.example.thicketstage.dto.request;

import com.example.thicketstage.domain.Stage;
import com.example.thicketstage.domain.StageStart;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class RequestCreateDateDto {

    private LocalDate date;

    private List<LocalTime> times;

    public StageStart toEntity(Stage stage) {
        return StageStart.createStageStart(stage, date, times);
    }
}
