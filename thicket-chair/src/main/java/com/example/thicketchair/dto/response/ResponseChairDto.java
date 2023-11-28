package com.example.thicketchair.dto.response;

import com.example.thicketchair.domain.Chair;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class ResponseChairDto {

    @NotEmpty
    private String chairType;
    @NotEmpty
    private int count;
    @NotEmpty
    private int price;
    @NotEmpty
    private Long stageId;

    public static ResponseChairDto toDto(Chair chair) {

        ResponseChairDto dto = new ResponseChairDto();

        dto.chairType = chair.getChairType();
        dto.count = chair.getCount();
        dto.price = chair.getPrice();
        dto.stageId = chair.getStageId();

        return dto;
    }
}
