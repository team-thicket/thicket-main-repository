package com.example.thicketstage.domain;

import com.example.thicketstage.TimeStamp;
import com.example.thicketstage.dto.request.RequestUpdateInfoDto;
import com.example.thicketstage.enumerate.StageStatus;
import com.example.thicketstage.enumerate.StageType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Stage extends TimeStamp {

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

    @Enumerated(EnumType.STRING)
    private StageType stageType;

    @Enumerated(EnumType.STRING)
    private StageStatus stageStatus;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "stage")
    private List<StageStart> stageStart = new ArrayList<>();

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
        this.stageType = updateInfoDto.getStageType();
        this.posterImg = updateInfoDto.getPosterImg();
        this.detailPosterImg = updateInfoDto.getDetailPosterImg();
        this.stageInfo = updateInfoDto.getStageInfo();
    }

    public void setStageStatus(StageStatus newStatus) {
        stageStatus = newStatus;
    }

    public static Stage createStage(String newName, String newPlace, LocalDateTime newStageOpen,
                                    LocalDateTime newStageClose, String newRunningTime,
                                    String newAgeLimit, StageType newStageType,
                                    StageStatus newStageStatus,
                                    String newPosterImg, String newDetailPosterImg,
                                    String newStageInfo) {
        Stage stage = new Stage();

        stage.name = newName;
        stage.place = newPlace;
        stage.stageOpen = newStageOpen;
        stage.stageClose = newStageClose;
        stage.runningTime = newRunningTime;
        stage.ageLimit = newAgeLimit;
        stage.stageType = newStageType;
        stage.stageStatus = newStageStatus;
        stage.posterImg = newPosterImg;
        stage.detailPosterImg = newDetailPosterImg;
        stage.stageInfo = newStageInfo;

        return stage;
    }
}
