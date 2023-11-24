package com.example.thicketchair.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RequestUpdateChairDto {

    @NotNull
    @Schema(description = "좌석타입", example = "R")
    private String newChairType;

    @Min(value = 0, message = "좌석은 음수로 들어올 수 없습니다.")
    @Max(value = 9999, message = "좌석는 9999개 이상 설정할 수 없습니다.")
    @NotNull(message = "재고는 반드시 입력되어야 합니다.")
    @Schema(description = "수량", example = "150")
    private int newCount;

    @Min(value = 0, message = "가격은 음수로 들어올 수 없습니다.")
    @NotNull(message = "가격은 반드시 입력되어야 합니다.")
    @Schema(description = "가격", example = "159000")
    private int newPrice;

    @NotNull
    private Long newStageId;
}
