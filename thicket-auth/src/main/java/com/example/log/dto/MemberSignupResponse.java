package com.example.log.dto;

import com.example.log.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class MemberSignupResponse {

    private String email;

    public static MemberSignupResponse from(Member member) {
        return MemberSignupResponse.builder()
                .email(member.getEmail())
                .build();
    }
}