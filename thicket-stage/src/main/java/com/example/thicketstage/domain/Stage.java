package com.example.thicketstage.domain;

import com.example.thicketstage.dto.request.RequestUpdateInfoDto;
import com.example.thicketstage.enumerate.StageStatus;
import com.example.thicketstage.enumerate.StageType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@AllArgsConstructor
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Stage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private String name;

    @Column(length = 300, nullable = false)
    private String place;

    @Column(length = 100, nullable = false)
    private LocalDateTime stageOpen;

    @Column(length = 100, nullable = false)
    private LocalDateTime stageClose;

    @Column(length = 100, nullable = false)
    private String runningTime;

    @Column(length = 100, nullable = false)
    private String ageLimit;

    @Column(length = 100, nullable = false)
    private int price;

    @Enumerated(EnumType.STRING)
    private StageType stageType;

    @Enumerated(EnumType.STRING)
    private StageStatus stageStatus;

    @Column(length = 100, nullable = false)
    private LocalDateTime stageStart;

    @Column(nullable = true)
    private String posterImg;

    @Column(nullable = true)
    private String detailPosterImg;

    @Column(nullable = true)
    private String stageInfo;


    // 비즈니스 로직
    public void updateStageInfo(RequestUpdateInfoDto updateInfoDto) {
        this.name = updateInfoDto.getName();
        this.place = updateInfoDto.getPlace();
        this.stageOpen = updateInfoDto.getStageOpen();
        this.stageClose = updateInfoDto.getStageClose();
        this.runningTime = updateInfoDto.getRunningTime();
        this.ageLimit = updateInfoDto.getAgeLimit();
        this.price = updateInfoDto.getPrice();
        this.stageType = updateInfoDto.getStageType();
        this.stageStart = updateInfoDto.getStageStart();
        this.posterImg = updateInfoDto.getPosterImg();
        this.detailPosterImg = updateInfoDto.getDetailPosterImg();
        this.stageInfo = updateInfoDto.getStageInfo();
    }

    public void setStageStatus(StageStatus newStatus) {
        stageStatus = newStatus;
    }

    // 테스트용 메서드
    public static Stage createStage(String newName, String newPlace, LocalDateTime newStageOpen,
                                    LocalDateTime newStageClose, String newRunningTime,
                                    String newAgeLimit, int newPrice, StageType newStageType,
                                    StageStatus newStageStatus, LocalDateTime newStageStart,
                                    String newPosterImg, String newDetailPosterImg, String newStageInfo) {
        Stage stage = new Stage();

        stage.name = newName;
        stage.place = newPlace;
        stage.stageOpen = newStageOpen;
        stage.stageClose = newStageClose;
        stage.runningTime = newRunningTime;
        stage.ageLimit = newAgeLimit;
        stage.price = newPrice;
        stage.stageType = newStageType;
        stage.stageStatus = newStageStatus;
        stage.stageStart = newStageStart;
        stage.posterImg = newPosterImg;
        stage.detailPosterImg = newDetailPosterImg;
        stage.stageInfo = newStageInfo;

        return stage;
    }
}
