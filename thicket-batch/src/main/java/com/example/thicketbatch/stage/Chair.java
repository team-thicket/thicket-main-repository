package com.example.thicketbatch.stage;


import com.example.thicketbatch.TimeStamp;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "chair", schema = "thicket_local_db")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Chair extends TimeStamp {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String chairType;

    @Column(nullable = false)
    private int count;

    @Column(nullable = false)
    private int availableCount;

    @Column(nullable = false)
    private int price;

    @ManyToOne(cascade = CascadeType.REMOVE)
    private StageStart stageStart;

    // 연관관계 메서드
    public void foreignKey(StageStart stageStart){
        this.stageStart = stageStart;
        stageStart.getChair().add(this);
    }

    // 비즈니스 로직
    public static Chair createChair(String chairType,
                                    int count,
                                    int price,
                                    StageStart stageStart) {
        Chair chair = new Chair();

        chair.chairType = chairType;
        chair.count = count;
        chair.price = price;
        chair.availableCount = 0;
        chair.foreignKey(stageStart);

        return chair;
    }


}
