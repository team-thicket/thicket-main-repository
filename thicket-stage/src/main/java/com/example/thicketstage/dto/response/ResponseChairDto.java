package com.example.thicketstage.dto.response;

import com.example.thicketstage.domain.Chair;
import lombok.Data;

import java.util.UUID;

@Data
public class ResponseChairDto {

    private String chairType;

    private int count;

    private int price;

    private UUID chairId;

    public ResponseChairDto(Chair chair) {
        this.chairType = chair.getChairType();
        this.count = chair.getCount();
        this.price = chair.getPrice();
        this.chairId = chair.getId();
    }
}
