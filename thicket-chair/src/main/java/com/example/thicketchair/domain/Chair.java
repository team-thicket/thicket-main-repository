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

    // 테스트용 메서드
    public static Chair createChair(String newChairType,
                                    int newCount,
                                    int newPrice) {
        Chair chair = new Chair();

        chair.chairType = newChairType;
        chair.count = newCount;
        chair.price = newPrice;

        return chair;
    }

    public void changeChair(String newChairType,
                            int newCount,
                            int newPrice) {

        chairType = newChairType;
        count = newCount;
        price = newPrice;
    }
}
