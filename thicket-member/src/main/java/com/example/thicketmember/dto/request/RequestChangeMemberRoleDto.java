package com.example.thicketmember.dto.request;

import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class RequestChangeMemberRoleDto {
    @Pattern(regexp="^(0-9{3})-([0-9]{2})-([0-9]{5})$")
    private String businessCode;
}
// "businessCode":"157-01-56156"