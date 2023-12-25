package com.example.thicketbatch.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
public class RequestCreateTicketDto implements Comparable<RequestCreateTicketDto>  {

    public int sequence;
    private UUID id;

    @NotBlank(message = "stageName cannot be empty")
    private String stageName;

    @NotBlank(message = "stageType cannot be empty")
    private String stageType;

    @NotNull(message = "DATE cannot be null")
    private LocalDateTime date;

    @NotBlank(message = "place cannot be empty")
    private String place;

    @NotBlank(message = "chairType cannot be empty")
    private String chairType;

    @NotNull(message = "count cannot be empty")
    private int count;

    @NotBlank(message = "memberName cannot be empty")
    private String memberName;

    @Min(value = 0, message = "price must be greater than or equal to 0")
    private int price;

    private String phone;

    @NotNull(message = "cancelDate cannot be null")
    private LocalDateTime cancelDate;

    @NotNull(message = "stageId cannot be null")
    private String stageId;

    @NotNull(message = "memberId cannot be null")
    private String memberId;

    @NotNull
    private boolean deleted;

    @NotNull
    private int latency;
    // 추가된 필드
    private String uuid;

    private LocalDateTime correctedTimestamp;



    public String toString() {
        return "RequestCreateTicketDto{" +
                "id=" + id +
                ", stageName='" + stageName + '\'' +
                ", stageType='" + stageType + '\'' +
                ", date=" + date +
                ", place='" + place + '\'' +
                ", chairType='" + chairType + '\'' +
                ", count=" + count +
                ", memberName='" + memberName + '\'' +
                ", price=" + price +
                ", phone='" + phone + '\'' +
                ", cancelDate=" + cancelDate +
                ", stageId='" + stageId + '\'' +
                ", memberId='" + memberId + '\'' +
                ", deleted=" + deleted +
                ", latency=" + latency +
                ", uuid='" + uuid + '\'' +
                ", correctedTimestamp=" + correctedTimestamp +
                '}';
    }

    @Override
    public int compareTo(RequestCreateTicketDto other) {
        // correctedTimestamp를 기준으로 정렬
        if (this.correctedTimestamp == null && other.correctedTimestamp == null) {
            return 0;
        } else if (this.correctedTimestamp == null) {
            return -1;
        } else if (other.correctedTimestamp == null) {
            return 1;
        } else {
            // LocalDateTime.compareTo를 사용하여 두 timestamp를 비교
            return this.correctedTimestamp.compareTo(other.correctedTimestamp);
        }
    }
}
