package com.example.thicketmember.dto.response;

import com.example.thicketmember.domain.Member;
import com.example.thicketmember.enumerate.MemberRole;
import lombok.Data;

@Data
public class ResponseMemberDtoForAdmin {
    private String email;
    private MemberRole role;

    public static ResponseMemberDtoForAdmin toDto(Member member){
        ResponseMemberDtoForAdmin newDto = new ResponseMemberDtoForAdmin();

        newDto.email = member.getEmail();
        newDto.role = member.getMemberRole();

        return newDto;
    }
}
