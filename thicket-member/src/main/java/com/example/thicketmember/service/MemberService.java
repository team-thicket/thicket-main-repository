package com.example.thicketmember.service;

import com.example.thicketmember.domain.Member;
import com.example.thicketmember.dto.ResponseMemberDto;

public interface MemberService {
    ResponseMemberDto getMemberByToken();
    void setNewPassword(String oldPw, String newPw);
    void setInactive(String pswd);
}
