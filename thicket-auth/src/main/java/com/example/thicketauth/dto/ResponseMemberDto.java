package com.example.thicketauth.dto;

import com.example.thicketauth.entity.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class ResponseMemberDto {

    private String name;
    private String email;
    private LocalDate birthdate;

    public static ResponseMemberDto from(Member member) {
        ResponseMemberDto responseDto = new ResponseMemberDto();
        responseDto.setName(member.getName());
        responseDto.setEmail(member.getEmail());
        responseDto.setBirthdate(member.getBirthdate());
        return responseDto;
    }
}

