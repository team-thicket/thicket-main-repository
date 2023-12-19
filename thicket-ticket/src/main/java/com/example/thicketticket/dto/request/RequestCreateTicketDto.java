package com.example.thicketticket.dto.request;

import com.example.thicketticket.domain.Ticket;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
public class RequestCreateTicketDto {

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


    public Ticket toEntity() {
        return Ticket.createTicket(
                stageName,place,date,
                chairType,count,
                memberName,phone,price,
                cancelDate,stageId,memberId,
                stageType
               );
    }



}
