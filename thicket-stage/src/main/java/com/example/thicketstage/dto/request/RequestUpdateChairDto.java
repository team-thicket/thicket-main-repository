package com.example.thicketstage.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
public class RequestUpdateChairDto {

    private List<UpdateChairDto> updateChairDtos;

    @Data
    @NoArgsConstructor
    public static class UpdateChairDto {
        @NotNull
        @Schema(description = "좌석타입", example = "VIP")
        private String chairType;

        @Min(value = 0, message = "좌석을 음수로 등록할 수 없습니다.")
        @Max(value = 99999, message = "좌석을 10만개 이상 설정할 수 없습니다.")
        @NotNull(message = "좌석 수량을 입력하세요.")
        @Schema(description = "수량", example = "100")
        private int count;

        @Min(value = 0, message = "가격을 음수로 등록할 수 없습니다.")
        @NotNull(message = "가격을 입력하세요.")
        @Schema(description = "가격", example = "189000")
        private int price;
    }
}
