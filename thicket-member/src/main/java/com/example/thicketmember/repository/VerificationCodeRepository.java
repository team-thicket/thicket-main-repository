package com.example.thicketmember.repository;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class VerificationCodeRepository {
    Map<String, String> codeRepository = new HashMap<>();
    public void save(String email, String code){
        codeRepository.put(email,code);
    }

    public String find(String email){
        return codeRepository.get(email);
    }

    public void delete(String email) {
        codeRepository.remove(email);
    }
}
