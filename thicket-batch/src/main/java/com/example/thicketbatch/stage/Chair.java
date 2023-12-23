package com.example.thicketbatch.stage;


import com.example.thicketbatch.TimeStamp;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
@Entity
@Table
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Chair extends TimeStamp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
                                    int availableCount,
                                    int price,
                                    StageStart stageStart) {
        Chair chair = new Chair();

        chair.chairType = chairType;
        chair.count = count;
        chair.availableCount = availableCount;
        chair.price = price;
        chair.foreignKey(stageStart);

        return chair;
    }

// 좌석 정보 수정은 추후 고도화 구현시 구현 예정


}
