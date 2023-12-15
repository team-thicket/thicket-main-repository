package com.example.thicketmember.dto.request;

import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;


@Data
@NoArgsConstructor
public class RequestChangeMemberRoleDto {
    private UUID id;
    @Pattern(regexp="^(0-9{3})-([0-9]{2})-([0-9]{5})$")
    private String businessCode;
}
// "businessCode":"157-01-56156"