package com.example.thicketauth.smtp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/")
@RestController
@RequiredArgsConstructor
public class TestController {
    @GetMapping("test")
    public String test(){
        return "hello world";
    }

}
