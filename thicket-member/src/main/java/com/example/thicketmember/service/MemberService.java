package com.example.thicketmember.service;

import com.example.thicketmember.dto.request.RequestInactiveDto;
import com.example.thicketmember.dto.request.RequestSetNewPasswordDto;
import com.example.thicketmember.dto.response.ResponseMemberDto;

public interface MemberService {
    ResponseMemberDto getMemberByToken(String email);
    void setNewPassword(String email, RequestSetNewPasswordDto dto);
    void setInactive(String email, RequestInactiveDto dto);
}
