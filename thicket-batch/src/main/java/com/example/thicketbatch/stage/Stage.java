package com.example.thicketbatch.stage;


import com.example.thicketbatch.TimeStamp;
import com.example.thicketbatch.enumerate.StageType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Entity
@Table
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
    private LocalDateTime ticketOpen;

    @Column(length = 100, nullable = false)
    private LocalDateTime stageOpen;

    @Column(length = 100, nullable = false)
    private LocalDateTime stageClose;

    @Column(length = 100, nullable = false)
    private LocalDateTime lastTicket;

    @Column(length = 100, nullable = false)
    private String runningTime;

    @Column(length = 100, nullable = false)
    private String ageLimit;

    @Enumerated(EnumType.STRING)
    private StageType stageType;

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "stage")
    private List<StageStart> stageStart = new ArrayList<>();

    @Column(nullable = true)
    private String posterImg;

    @Column(nullable = true)
    private String detailPosterImg;

    @Column(nullable = true)
    private String stageInfo;




    public static Stage createStage(String newName, String newPlace, LocalDateTime newTicketOpen,
                                    LocalDateTime newStageOpen, LocalDateTime newStageClose,
                                    LocalDateTime newLastTicket, String newRunningTime,
                                    String newAgeLimit, StageType newStageType,
                                    String newPosterImg, String newDetailPosterImg,
                                    String newStageInfo) {
        Stage stage = new Stage();

        stage.name = newName;
        stage.place = newPlace;
        stage.ticketOpen = newTicketOpen;
        stage.stageOpen = newStageOpen;
        stage.stageClose = newStageClose;
        stage.lastTicket = newLastTicket;
        stage.runningTime = newRunningTime;
        stage.ageLimit = newAgeLimit;
        stage.stageType = newStageType;
        stage.posterImg = newPosterImg;
        stage.detailPosterImg = newDetailPosterImg;
        stage.stageInfo = newStageInfo;

        return stage;
    }
}
