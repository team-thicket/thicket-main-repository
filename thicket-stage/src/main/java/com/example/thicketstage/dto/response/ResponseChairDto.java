package com.example.thicketstage.dto.response;

import com.example.thicketstage.domain.Chair;
import com.example.thicketstage.domain.StageStart;
import jakarta.validation.constraints.NotEmpty;
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
//        this.stageUuid = stageStart.getUuid();
    }

//    public static ResponseChairDto toDto(Chair chair) {
//
//        ResponseChairDto dto = new ResponseChairDto();
//
//        dto.chairType = chair.getChairType();
//        dto.count = chair.getCount();
//        dto.price = chair.getPrice();
//        dto.stageUuid = chair.getStageUuid();
//
//        return dto;
//    }
}
