package com.example.thicketstage.dto.request;

import com.example.thicketstage.enumerate.StageStatus;
import com.example.thicketstage.enumerate.StageType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import java.time.LocalDateTime;

@Getter @Setter @NoArgsConstructor
@AllArgsConstructor @Builder @ToString
public class RequestUpdateInfoDto {

    @NotNull(message = "공연명은 필수 입력 항목입니다.")
    private String name;

    @NotNull(message = "공연장소는 필수 입력 항목입니다.")
    private String place;

    @NotNull(message = "개막일은 필수 입력 항목입니다.")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime stageOpen;

    @NotNull(message = "폐막일은 필수 입력 항목입니다.")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime stageClose;

    @NotNull(message = "러닝타임은 필수 입력 항목입니다.")
    private String runningTime;

    @NotNull(message = "연령 제한은 필수 입력 항목입니다.")
    private String ageLimit;

    @NotNull(message = "가격은 필수 입력 항목입니다.")
    @NumberFormat(pattern = "#,###")
    @Positive(message = "가격은 0보다 커야 합니다.")
    private int price;

    @NotNull(message = "공연 타입은 필수 입력 항목입니다. (PLAY, MUSICAL, CONCERT)")
    @Enumerated(EnumType.STRING)
    private StageType stageType;

    @NotNull(message = "공연 시작 시간은 필수 입력 항목입니다.")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime stageStart;


    private String posterImg;

    private String detailPosterImg;

    private String stageInfo;
}
