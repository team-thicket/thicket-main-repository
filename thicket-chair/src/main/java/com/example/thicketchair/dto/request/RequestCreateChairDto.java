package com.example.thicketchair.dto.request;

import com.example.thicketchair.domain.Chair;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RequestCreateChairDto {

    @NotNull
    @Schema(description = "좌석타입", example = "VIP")
    private String chairType;

    @Min(value = 0, message = "좌석은 음수로 들어올 수 없습니다.")
    @Max(value = 9999, message = "좌석는 9999개 이상 설정할 수 없습니다.")
    @NotNull(message = "재고는 반드시 입력되어야 합니다.")
    @Schema(description = "수량", example = "100")
    private int count;

    @Min(value = 0, message = "가격은 음수로 들어올 수 없습니다.")
    @NotNull(message = "가격은 반드시 입력되어야 합니다.")
    @Schema(description = "가격", example = "189000")
    private int price;

    @NotNull
    private Long stageId;

    public Chair toEntity() {
        return Chair.builder()
                .chairType(this.chairType)
                .count(this.count)
                .price(this.price)
                .stageId(this.stageId).build();
    }
}
