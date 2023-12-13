package com.example.thicketstage.dto.response;

import com.example.thicketstage.domain.Chair;
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
    private String stageUuid;

    public static ResponseChairDto toDto(Chair chair) {

        ResponseChairDto dto = new ResponseChairDto();

        dto.chairType = chair.getChairType();
        dto.count = chair.getCount();
        dto.price = chair.getPrice();
        dto.stageUuid = chair.getStageUuid();

        return dto;
    }
}
