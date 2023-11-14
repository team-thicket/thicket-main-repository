package com.example.thicketmember.service;

import org.springframework.http.ResponseEntity;

public interface MemberService {
    ResponseEntity<?> getMemberByEmail(String email);
    ResponseEntity<?> setNewPassword(String oldPw, String newPw);
    ResponseEntity<?> setInactive(String pswd);
}
