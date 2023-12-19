package com.example.thicketstage.dto.request;

import com.example.thicketstage.domain.Stage;
import com.example.thicketstage.enumerate.StageType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class RequestCreateStageDto {

    @NotBlank(message = "공연명은 필수 입력 항목입니다.")
    private String name;

    @NotBlank(message = "공연장소는 필수 입력 항목입니다.")
    private String place;

    @NotNull(message = "티켓오픈일은 필수 입력 항목입니다.")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime ticketOpen;

    @NotNull(message = "개막일은 필수 입력 항목입니다.")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime stageOpen;

    @NotNull(message = "폐막일은 필수 입력 항목입니다.")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime stageClose;

    @NotNull(message = "티켓마감일은 필수 입력 항목입니다.")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime lastTicket;

    @NotBlank(message = "러닝타임은 필수 입력 항목입니다.")
    private String runningTime;

    @NotBlank(message = "연령 제한은 필수 입력 항목입니다.")
    private String ageLimit;

    private StageType stageType;

    private String posterImg;

    private String detailPosterImg;

    private String stageInfo;

    public Stage toEntity() {
        return Stage.createStage(
                name,place, ticketOpen,
                stageOpen,stageClose,
                lastTicket, runningTime,
                ageLimit, stageType,
                posterImg,
                detailPosterImg,stageInfo);
    }
}