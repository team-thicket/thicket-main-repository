package com.example.thicketstage.dto.response;

import com.example.thicketstage.domain.Chair;
import lombok.Data;

@Data
public class ResponseChairDto {

    private String chairType;

    private int count;

    private int price;

    private String chairUuid;

    public ResponseChairDto(Chair chair) {
        this.chairType = chair.getChairType();
        this.count = chair.getCount();
        this.price = chair.getPrice();
        this.chairUuid = chair.getUuid();
    }
}
