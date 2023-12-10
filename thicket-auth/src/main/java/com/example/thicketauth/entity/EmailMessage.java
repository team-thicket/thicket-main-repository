package com.example.thicketauth.entity;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmailMessage {

    private String to;
    private String subject;
    private String message;
}