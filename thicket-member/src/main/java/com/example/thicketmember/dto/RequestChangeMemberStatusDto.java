package com.example.thicketmember.dto;

import com.example.thicketmember.enumerate.MemberStatus;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class RequestChangeMemberStatusDto {

    private MemberStatus status = MemberStatus.ACTIVE;

}
