package com.example.thicketstage.dto.request;

import com.example.thicketstage.enumerate.StageType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter @Setter @NoArgsConstructor
@AllArgsConstructor @Builder @ToString
public class RequestUpdateInfoDto {

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

    private StageType stageType;

    private String posterImg;

    private String detailPosterImg;

    private String stageInfo;

}