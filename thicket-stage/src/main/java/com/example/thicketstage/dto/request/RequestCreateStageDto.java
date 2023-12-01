package com.example.thicketstage.dto.request;

import com.example.thicketstage.domain.Stage;
import com.example.thicketstage.enumerate.StageStatus;
import com.example.thicketstage.enumerate.StageType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import java.time.LocalDateTime;

@Getter @Setter @NoArgsConstructor
@AllArgsConstructor @Builder @ToString
public class RequestCreateStageDto {

    @NotBlank(message = "공연명은 필수 입력 항목입니다.")
    private String name;

    @NotBlank(message = "공연장소는 필수 입력 항목입니다.")
    private String place;

    @NotNull(message = "개막일은 필수 입력 항목입니다.")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime stageOpen;

    @NotNull(message = "폐막일은 필수 입력 항목입니다.")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime stageClose;

    @NotBlank(message = "러닝타임은 필수 입력 항목입니다.")
    private String runningTime;

    @NotBlank(message = "연령 제한은 필수 입력 항목입니다.")
    private String ageLimit;

    @NotNull(message = "가격은 필수 입력 항목입니다.")
    @NumberFormat(pattern = "#,###")
    @Positive(message = "가격은 0보다 커야 합니다.")
    private int price;

//    @NotBlank(message = "공연 타입은 필수 입력 항목입니다. (PLAY, MUSICAL, CONCERT)")
//    @Enumerated(EnumType.STRING)
    private StageType stageType;

//    @NotBlank(message = "공연 상태는 필수 입력 항목입니다.")
//    @Enumerated(EnumType.STRING)
    private StageStatus stageStatus;

    @NotNull(message = "공연 시작 시간은 필수 입력 항목입니다.")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime stageStart;


    private String posterImg;

    private String detailPosterImg;

    private String stageInfo;

    public Stage toEntity(){
        return Stage.builder()
                .name(this.name)
                .place(this.place)
                .stageOpen(this.stageOpen)
                .stageClose(this.stageClose)
                .runningTime(this.runningTime)
                .ageLimit(this.ageLimit)
                .price(this.price)
                .stageType(this.stageType)
                .stageStatus(this.stageStatus)
                .stageStart(this.stageStart)
                .posterImg(this.posterImg)
                .detailPosterImg(this.detailPosterImg)
                .stageInfo(this.stageInfo)
                .build();
    }

    // 테스트용 메서드
    public Long getId() {
        return 1L;
    }
}
