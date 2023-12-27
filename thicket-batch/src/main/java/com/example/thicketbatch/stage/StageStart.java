package com.example.thicketbatch.stage;

import com.example.thicketbatch.TimeStamp;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StageStart extends TimeStamp {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(length = 100, nullable = false)
    private LocalDate date;

    @Column(length = 100, nullable = false)
    private LocalTime time;

    @ManyToOne(fetch = FetchType.LAZY)
    private Stage stage;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "stageStart", cascade = CascadeType.REMOVE)
    private List<Chair> chair = new ArrayList<>();





}
