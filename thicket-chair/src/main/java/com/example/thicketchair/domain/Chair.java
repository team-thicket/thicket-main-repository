package com.example.thicketchair.domain;

import com.example.thicketchair.TimeStamp;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
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
    private int price;

    @Column(nullable = false)
    private Long stageId;

    // 테스트용 메서드
    public static Chair createChair(String chairType,
                                    int count,
                                    int price,
                                    Long stageId) {
        Chair chair = new Chair();

        chair.chairType = chairType;
        chair.count = count;
        chair.price = price;
        chair.stageId = stageId;

        return chair;
    }

    public void changeChair(String newChairType,
                            int newCount,
                            int newPrice,
                            Long newStageId) {

        chairType = newChairType;
        count = newCount;
        price = newPrice;
        stageId = newStageId;

    }
}
