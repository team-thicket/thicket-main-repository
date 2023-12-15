package com.example.thicketmember.service;

import com.example.thicketmember.domain.Member;
import com.example.thicketmember.dto.request.*;
import com.example.thicketmember.dto.response.ResponseMemberDto;
import com.example.thicketmember.dto.response.ResponseMemberDtoForAdmin;

import java.util.List;

public interface MemberService {
    Member signup(RequestMemberSignupDto dto);
    Member signin(RequestMemberSigninDto dto);
    ResponseMemberDto findMember(String id);
    List<ResponseMemberDtoForAdmin> findMembers();
    void changePassword(String id, RequestChangePasswordDto dto);
    void withdraw(String id, RequestWithdrawDto dto);
    void changeMemberRole(String id, RequestChangeMemberRoleDto dto);
}
