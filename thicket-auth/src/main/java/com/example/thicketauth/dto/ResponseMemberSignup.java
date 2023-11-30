package com.example.thicketauth.dto;

import com.example.thicketauth.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class ResponseMemberSignup {

    private String email;

    public static ResponseMemberSignup from(Member member) {
        return ResponseMemberSignup.builder()
                .email(member.getEmail())
                .build();
    }
}