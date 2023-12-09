package com.example.thicketticket.dto.request;

import com.example.thicketticket.domain.Ticket;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class RequestCreateTicketDto {

    @NotBlank(message = "stageName cannot be empty")
    private String stageName;

    @NotNull(message = "DATE cannot be null")
    private String date;

    @NotBlank(message = "place cannot be empty")
    private String place;

    @NotBlank(message = "chairType cannot be empty")
    private String chairType;

    @NotBlank(message = "memberName cannot be empty")
    private String memberName;

    @Min(value = 0, message = "price must be greater than or equal to 0")
    private int price;


    private String ticketNumber;

    @NotNull(message = "stageId cannot be null")
    private Long stageId;

    @NotNull(message = "memberId cannot be null")
    private Long memberId;


    public Ticket toEntity() {
        return Ticket.builder()
                .stageName(this.stageName)
                .date(this.date)
                .place(this.place)
                .chairType(this.chairType)
                .memberName(this.memberName)
                .price(this.price)
                .ticketNumber(UUID.randomUUID().toString())
                .stageId(this.stageId)
                .memberId(this.memberId)
                .build();
    }
}
