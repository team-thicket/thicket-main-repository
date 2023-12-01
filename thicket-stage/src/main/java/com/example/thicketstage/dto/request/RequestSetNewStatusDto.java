package com.example.thicketstage.dto.request;

import com.example.thicketstage.enumerate.StageStatus;
import lombok.*;

@Getter @Setter @NoArgsConstructor
@AllArgsConstructor @Builder @ToString
public class RequestSetNewStatusDto {

    private StageStatus newStatus;
//            = StageStatus.BEFORE;
}
