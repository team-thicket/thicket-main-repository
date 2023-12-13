package com.example.thicketmember.dto;

import lombok.*;

@Data
public class EmailMessage {

    private String to;
    private String subject;
    private String message;
}
