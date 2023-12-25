package com.example.thicketstage.dto.request;

import com.amazonaws.services.s3.model.ObjectMetadata;
import com.example.thicketstage.domain.Stage;
import com.example.thicketstage.enumerate.StageType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@Slf4j
public class RequestCreateStageDto {
    @NotBlank(message = "공연명은 필수 입력 항목입니다.")
    private String name;

    @NotBlank(message = "공연장소는 필수 입력 항목입니다.0")
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

    private String imgLink;

    private List<String> detailImgLink;

    private String stageInfo;

    public Stage toEntity() {
        return Stage.createStage(
                name,place, ticketOpen,
                stageOpen,stageClose,
                lastTicket, runningTime,
                ageLimit, stageType,
                imgLink, String.join("&", detailImgLink)
                ,stageInfo);
    }
}