package com.example.thicketmember.dto.response;

import com.example.thicketmember.domain.Member;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ResponseMemberDto {

    @NotEmpty
    private String name;
    
    private LocalDate birth;

    @Email
    private String email;

    public static ResponseMemberDto toDto(Member member) {

        ResponseMemberDto dto = new ResponseMemberDto();

        dto.name = member.getName();
        dto.birth = member.getBirth();
        dto.email = member.getEmail();

        return dto;
    }
}
