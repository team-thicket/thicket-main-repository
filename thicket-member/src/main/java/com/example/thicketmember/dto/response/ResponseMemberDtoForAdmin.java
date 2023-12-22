package com.example.thicketmember.dto.response;

import com.example.thicketmember.domain.Member;
import com.example.thicketmember.enumerate.MemberRole;
import lombok.Data;

import java.util.UUID;

@Data
public class ResponseMemberDtoForAdmin {
    private UUID uuid;
    private String email;
    private MemberRole role;

    public static ResponseMemberDtoForAdmin toDto(Member member){
        ResponseMemberDtoForAdmin newDto = new ResponseMemberDtoForAdmin();

        newDto.uuid = member.getId();
        newDto.email = member.getEmail();
        newDto.role = member.getMemberRole();

        return newDto;
    }
}
