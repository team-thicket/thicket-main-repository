package com.example.thicketstage.dto.response;

import com.example.thicketstage.domain.Chair;
import lombok.Data;

@Data
public class ResponseChairDto {

    private String chairType;

    private int count;

    private int price;

    private String stageStartUuid;

    public ResponseChairDto(Chair chair) {
        this.chairType = chair.getChairType();
        this.count = chair.getCount();
        this.price = chair.getPrice();
        this.stageStartUuid = chair.getUuid();
    }
}
